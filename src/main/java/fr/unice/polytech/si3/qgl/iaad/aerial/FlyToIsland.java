package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 *         <p>
 *         Vol jusqu'à l'île
 */
class FlyToIsland implements Protocol
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
     * Direction vers laquelle se trouve l'île
     */
    private Direction target;
    /**
     * Conservation du sens de parcours de la carte pour l'exploration de l'île
     */
    private Direction sense;
    /**
     * la distance séparant le drone et l'île
     */
    private int range;

    /**
     * @param heading l'orientation du drone
     * @param target  la direction vers laquelle se trouve l'île
     * @param range   la distance a parcourir pour atteindre l'île
     */
    FlyToIsland(IslandMap map, Direction heading, Direction target, Direction sense, int range)
    {
        this.map = map;
        this.heading = heading;
        this.target = target;
        this.range = range;
        this.sense = sense;
    }

    /**
     * @return Heading si le drone n'est pas dans la bonne direction
     * sinon Fly
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        range -= 1;
        if (map.getNumberOfAvailablePoints(heading) < 1)
            return new Stop();
        map.moveDrone(heading);
        if (heading == target)
            return new Fly();
        if (map.isDirectionFinished(target) && map.getNumberOfAvailablePoints(target) < 1)
            return new Stop();
        if (sense == null)
            sense = heading;
        heading = target;
        map.moveDrone(target);
        return new Heading(target);
    }

    /**
     * @param result le résultat de l'action effectué
     * @return ScanIsland si on a atteint l'île
     * FlyToIsland sinon
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        if (sense == null)
            sense = heading.getRight();
        if (range > 0)
            return this;
        if (sense == heading)
            return new Turn(new ScanIsland(map, heading.getRight(), sense), map, heading, heading.getRight());
        if (sense.getBack() == heading)
            return new Turn(new ScanIsland(map, heading.getRight(), sense.getBack()), map, heading, heading.getRight());
        if (range == 0)
            return this;
        if (Drone.SEARCH_EMERGENCY_SITE)
            return new ScanIsland(map, heading, sense);
        return new ScanBeach(map, heading, sense);
    }
}
