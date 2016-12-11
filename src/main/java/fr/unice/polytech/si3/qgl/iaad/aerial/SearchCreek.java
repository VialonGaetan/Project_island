package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 * Parcours l'île pour trouver une crique
 */
public class SearchCreek implements Protocol
{
    private IslandMap map;
    private Direction direction;
    private Direction sense;
    private Protocol protocol;

    /**
     * @param direction l'orientation du drone
     * @param sense le sens dans lequel on parcours l'île
     *              (On balaye l'île de bout en bout en suivant en sens de parcours,
     *              puis, lorsque le drone atteint la limite de l'île,
     *              le drone fait demi-tour en inversant le sens de parcours
     *              Après un aller-retour, l'île a été entièrement parcourue par le drone)
     */
    SearchCreek(IslandMap map, Direction direction, Direction sense)
    {
        this.map = map;
        this.direction = direction;
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
     * On Fly au dessus de l'île
     */
    private class FlyOnIsland implements Protocol
    {
        @Override
        public Action nextAction() throws InvalidMapException
        {
            map.moveDrone(direction);
            return new Fly();
        }

        @Override
        public Protocol setResult(AreaResult result)
        {
            return new ScanToFindCreek();
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
         * @param result le résultat de l'action effectué
         * @return Land si on trouve une crique
         *         FlyOnIsland si on est toujours sur l'île
         *         ReturnToIsland sinon pour revenir sur l'île
         */
        @Override
        public Protocol setResult(AreaResult result) throws InvalidMapException
        {
            if (map.getBiomes(map.getDroneCoordinates()).length > 1) return new Land();
            for (int i=0; i<result.nbBiomes(); i++)
                map.addBiomes(Element.valueOf(result.getBiome(i)));
            for (int i=0; i<result.nbCreeks(); i++)
                map.addCreeks(result.getCreeks(i));
            if (result.getSites() != null)
                map.addEmergencySite(result.getSites());

            if (map.getNumberOfAvailablePoints(direction) < 1 && map.isDirectionFinished(direction))
                return new Land();
            if (result.nbBiomes() > 1 || Element.valueOf(result.getBiome(0)) != Element.OCEAN)
                return new FlyOnIsland();
            return new ReturnToIsland(map, direction, sense);
        }
    }
}
