package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.awt.*;

/**
 * Le drone longe les côtes de l'île pour trouver une crique.
 * Définition de côtes: Une tuile composée de au moins deux biomes dont un OCEAN.
 * <p>
 * Created the 16/12/2016.
 *
 * @author Alexandre Clement
 */
public class ScanBeach implements Protocol
{
    /**
     * La carte utilisée
     */
    private IslandMap map;
    /**
     * L'orientation du drone
     */
    private Direction heading;
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;
    /**
     * Conservation du sens de parcours de la carte pour l'exploration de l'île
     */
    private Direction sense;

    /**
     * @param map     la map actuelle
     * @param heading l'orientation du drone
     * @param sense   le sens de recherche
     */
    ScanBeach(IslandMap map, Direction heading, Direction sense)
    {
        this.map = map;
        this.heading = heading;
        this.sense = sense;
        protocol = new ScanToFindCreek();
    }

    @Override
    public Action nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        // protocol =
        return protocol.setResult(result);
    }

    /**
     * On Fly au dessus des côtes
     */
    private class FlyOnBeach implements Protocol
    {
        /**
         * @return Fly
         * @throws InvalidMapException si on sort de la carte
         */
        @Override
        public Action nextAction() throws InvalidMapException
        {
            if (map.getNumberOfAvailablePoints(heading) < 1)
                return new Stop();
            map.moveLocation(heading);
            return new Fly();
        }

        /**
         * @param result le résultat de l'action effectué
         * @return On reprend le protocole ScanBeach
         */
        @Override
        public Protocol setResult(Area result)
        {
            return new ScanBeach(map, heading, sense);
        }
    }

    /**
     * On Scan pour trouver une crique
     */
    private class ScanToFindCreek implements Protocol
    {
        /**
         * @return Scan
         */
        @Override
        public Action nextAction()
        {
            return new Scan();
        }

        /**
         * On ajoute les éléments trouver à la map
         *
         * @param result le résultat de l'action effectué
         * @return Land si on trouve une crique
         * FlyOnBeach si on il y a au moins deux biomes dont un OCEAN
         * Sinon, turn pour revenir vers les côtes
         */
        @Override
        public Protocol setResult(Area result) throws InvalidMapException
        {
            // on ajoute les biomes à la carte
            for (int i = 0; i < result.nbBiomes(); i++)
            {
                map.addBiomes(Element.valueOf(result.getBiome(i)));
            }
            // Si on trouve une crique, on Land dessus
            if (result.nbCreeks() > 0)
            {
                map.addCreeks(result.getCreeks(0));
                return new LandOnIsland(map, map.getLocation());
            }
            // Si il n'y a que de l'OCEAN, alors on tourne pour revenir vers une côte
            if (result.nbBiomes() == 1 && Element.valueOf(result.getBiome(0)) == Element.OCEAN)
                return turn(sense, heading.getBack());
            // Si il y a au moins deux biomes dont un OCEAN, alors on est sur une côte
            for (int i = 0; i < result.nbBiomes(); i++)
            {
                if (Element.valueOf(result.getBiome(i)) == Element.OCEAN)
                    return new FlyOnBeach();
            }
            // Sinon, on est au dessus de l'île, sans OCEAN et donc, on tourne pour revenir vers une côte
            return turn(sense.getBack(), heading);
        }

        /**
         * Tourne dans le direction voulue, si possible et si cela n'a pas déjà été fait
         *
         * @param target la direction cibler
         * @param sense  le sens de recherche après avoir tournée
         * @return le nouveau protocole
         * @throws InvalidMapException si on sort de la carte
         */
        private Protocol turn(Direction target, Direction sense) throws InvalidMapException
        {
            if (canDroneMoveToAndNotAlreadyVisited(target))
                return turnBuilder(target, sense);

            if (canDroneMoveToAndNotAlreadyVisited(target.getBack()))
                return turnBuilder(target.getBack(), sense.getBack());

            if (map.getNumberOfAvailablePoints(heading) > 1)
                return new FlyOnBeach();

            if (canDroneMoveTo(target))
                return turnBuilder(target, sense);

            if (canDroneMoveTo(target.getBack()))
                return turnBuilder(target.getBack(), sense.getBack());

            return new StopAerial();
        }

        /**
         * Calcule la position sur laquelle le drone se situera après avoir tourner dans la direction donnée.
         *
         * @param direction la direction vers laquelle tourne le drone.
         * @return la position d'arrivée.
         */
        private Point turnTo(Direction direction)
        {
            Point position = new Point(map.getLocation());
            position.translate(heading.getVecteur().x, heading.getVecteur().y);
            position.translate(direction.getVecteur().x, direction.getVecteur().y);
            return position;
        }

        /**
         * Vérifie si le drone peut tourner dans la direction donnée.
         *
         * @param direction la direction vers laquelle tourne le drone.
         * @return true si le drone peut tourner dans la direction donnée, false sinon.
         */
        private boolean canDroneMoveTo(Direction direction)
        {

            return map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(direction) > 1;
        }

        /**
         * Vérifie si une case a déjà été visitée.
         *
         * @param position la position a vérifié.
         * @return true si la position a déjà été visité, false sinon.
         */
        private boolean alreadyVisited(Point position) throws InvalidMapException
        {
            return map.getBiomes(position).length != 0;
        }

        /**
         * Vérifie si le drone peut tourner dans la direction donnée et si la case n'a pas déjà été visitée.
         *
         * @param direction la direction vers laquelle le drone tourne.
         * @return true si le drone peut tourner vers la case qui n'a pas encore été visitée, false sinon.
         */
        private boolean canDroneMoveToAndNotAlreadyVisited(Direction direction) throws InvalidMapException
        {
            Point position = turnTo(direction);
            return canDroneMoveTo(direction) && !alreadyVisited(position);
        }

        private Turn turnBuilder(Direction target, Direction sense) throws InvalidMapException
        {
            return new Turn(new ScanBeach(map, target, sense), map, heading, target);
        }
    }
}
