package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 * Fly jusqu'à l'île
 */
public class FlyToIsland implements Protocol
{
    private IslandMap map;
    private Direction heading;
    private Direction target;
    private Direction sense;
    private int range;

    /**
     * @param heading l'orientation du drone
     * @param target la direction vers laquelle se trouve l'île
     * @param range la distance a parcourir pour atteindre l'île
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
     *         sinon Fly
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
        map.moveDrone(heading);
        return new Heading(target);
    }

    /**
     * @param result le résultat de l'action effectué
     * @return SearchCreek si on a atteint l'île
     *         FlyToIsland sinon
     */
    @Override
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        if (sense == null)
            sense = heading.getRight();
        if (range > 0)
            return this;
        if (sense == heading)
            return new Turn(new SearchCreek(map, heading.getRight(), sense), map, heading, heading.getRight());
        if (sense.getBack() == heading)
            return new Turn(new SearchCreek(map, heading.getRight(), sense.getBack()), map, heading, heading.getRight());
        if (range == 0)
            return this;
        return new SearchCreek(map, heading, sense);
    }
}
