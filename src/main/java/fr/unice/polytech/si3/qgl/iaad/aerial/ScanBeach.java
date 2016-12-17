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
 *         On longe les rives de l'île pour trouver une crique
 */
public class ScanBeach implements Protocol
{
    private Protocol protocol;
    private IslandMap map;
    private Direction heading;
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
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        return protocol = protocol.setResult(result);
    }

    /**
     * Tourne dans le direction voulue si possible et si cela n'a pas déjà été fait
     *
     * @param target la direction cibler
     * @param sense  le sens de recherche après avoir tournée
     * @return le nouveau protocole
     * @throws InvalidMapException si on sort de la carte
     */
    private Protocol turn(Direction target, Direction sense) throws InvalidMapException
    {
        Point position = new Point(map.getDroneCoordinates());
        position.translate(heading.getVecteur().x, heading.getVecteur().y);
        position.translate(target.getVecteur().x, target.getVecteur().y);
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target) > 1 && map.getBiomes(position).length == 0)
            return new Turn(new ScanBeach(map, target, sense), map, heading, target);
        position = new Point(map.getDroneCoordinates());
        position.translate(heading.getVecteur().x, heading.getVecteur().y);
        position.translate(target.getBack().getVecteur().x, target.getBack().getVecteur().y);
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target.getBack()) > 1 && map.getBiomes(position).length == 0)
            return new Turn(new ScanBeach(map, target.getBack(), sense.getBack()), map, heading, target.getBack());
        if (map.getNumberOfAvailablePoints(heading) > 1)
            return new FlyOnBeach();
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target) > 1)
            return new Turn(new ScanBeach(map, target, sense), map, heading, target);
        if (map.getNumberOfAvailablePoints(heading) > 0 && map.getNumberOfAvailablePoints(target.getBack()) > 1)
            return new Turn(new ScanBeach(map, target.getBack(), sense.getBack()), map, heading, target.getBack());
        return new StopAerial();
    }

    /**
     * On Fly au dessus des côtes
     */
    private class FlyOnBeach implements Protocol
    {
        @Override
        public Action nextAction() throws InvalidMapException
        {
            if (map.getNumberOfAvailablePoints(heading) < 1)
                return new Stop();
            map.moveDrone(heading);
            return new Fly();
        }

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
         * Sinon, turn
         */
        @Override
        public Protocol setResult(AreaResult result) throws InvalidMapException
        {
            if (result.nbCreeks() > 0)
            {
                map.addCreeks(result.getCreeks(0));
                return new LandOnIsland(map, map.getDroneCoordinates());
            }
            for (int i = 0; i < result.nbBiomes(); i++)
            {
                map.addBiomes(Element.valueOf(result.getBiome(i)));
            }
            if (result.nbBiomes() == 1 && Element.valueOf(result.getBiome(0)) == Element.OCEAN)
                return turn(sense, heading.getBack());
            for (int i = 0; i < result.nbBiomes(); i++)
            {
                if (Element.valueOf(result.getBiome(i)) == Element.OCEAN)
                    return new FlyOnBeach();
            }
            return turn(sense.getBack(), heading);
        }
    }
}
