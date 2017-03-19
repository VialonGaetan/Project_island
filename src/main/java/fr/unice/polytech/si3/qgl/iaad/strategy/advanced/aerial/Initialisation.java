package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial.FindIsland;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Alexandre Clement
 * @since 02/03/2017.
 */
public class Initialisation extends Aerial implements Protocol
{
    private final Deque<Compass> directions;
    private Protocol exit;

    public Initialisation(Context context, IslandMap islandMap, Drone drone)
    {
        super(context, islandMap, drone);
        exit = new FindIsland(context, islandMap, drone);
        directions = new ArrayDeque<>();
    }

    @Override
    public Decision takeDecision()
    {
        if (directions.isEmpty())
            setupDirections();
        return new Echo(directions.peek());
    }

    private void setupDirections()
    {
        directions.add(drone.getHeading());
        directions.add(drone.getHeading().get(Direction.RIGHT));
        directions.add(drone.getHeading().get(Direction.LEFT));
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        EchoResult echoResult = new EchoResult(result);
        Compass direction = directions.pop();
        growIslandMap(direction, echoResult.getRange());

        if (directions.isEmpty())
            return exit;

        if (echoResult.getFound() == Element.GROUND)
            exit = new FindLimit(exit, context, islandMap, drone, direction);

        return this;
    }

    private void growIslandMap(Compass direction, int range)
    {
        if (range - distanceToLimit(direction) > 0)
            islandMap.grow(direction, range - distanceToLimit(direction));
    }
}
