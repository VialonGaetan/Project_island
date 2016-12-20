package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

import java.awt.*;

/**
 * @author Alexandre Clement
 *         Created the 16/12/2016.
 *         <p>
 *         On longe les côtes de l'île pour trouver une crique
 *         <p>
 *         Définition de côtes: Une tuile composée de au moins deux biomes dont un OCEAN
 */
class ScanBeach implements Protocol
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
     * todo: ne pas SCAN si cela a déjà été fait
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
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        return protocol = protocol.setResult(result);
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
        // On vérifie que l'on est pas déjà aller sur la tuile cibler
        Point position;
        position = new Point(map.getDroneCoordinates());
        position.translate(heading.getVecteur().x, heading.getVecteur().y);
        position.translate(target.getVecteur().x, target.getVecteur().y);
        // Si on est jamais passer dessus, et si la tuile n'est pas hors carte, alors on tourne vers celle-ci
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target) > 1 && map.getBiomes(position).length == 0)
            return new Turn(new ScanBeach(map, target, sense), map, heading, target);
        // Sinon, on vérifie que l'on est pas déjà aller sur la tuile dans le sens opposé
        position = new Point(map.getDroneCoordinates());
        position.translate(heading.getVecteur().x, heading.getVecteur().y);
        position.translate(target.getBack().getVecteur().x, target.getBack().getVecteur().y);
        // Si on est jamais passer dessus, et si la tuile n'est pas hors carte, alors on tourne vers celle-ci
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target.getBack()) > 1 && map.getBiomes(position).length == 0)
            return new Turn(new ScanBeach(map, target.getBack(), sense.getBack()), map, heading, target.getBack());
        // Sinon, si on a au moins deux tuiles devant le drone, on continue tout droit
        if (map.getNumberOfAvailablePoints(heading) > 1)
            return new FlyOnBeach();
        // Sinon, on tourne vers la direction initialement cibler mais sans la SCAN
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target) > 1)
            return new Turn(new ScanBeach(map, target, sense), map, heading, target);
        // Sinon, on tourne vers la direction opposé mais sans la SCAN
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target.getBack()) > 1)
            return new Turn(new ScanBeach(map, target.getBack(), sense.getBack()), map, heading, target.getBack());
        // Sinon, le drone est bloquer, on arrête la partie
        return new StopAerial();
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
        public Protocol setResult(AreaResult result)
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
        public Protocol setResult(AreaResult result) throws InvalidMapException
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
                return new LandOnIsland(map, map.getDroneCoordinates());
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
    }
}
