package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.EchoResultat;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class EchoInDirection implements Protocol
{
    private final Context context;
    private final IslandMap islandMap;
    private final Drone drone;
    private final Deque<Compass> direction;

    EchoInDirection(Context context, IslandMap islandMap, Drone drone, Compass... direction)
    {
        this(context, islandMap, drone, new ArrayDeque<>(Arrays.asList(direction)));
    }

    private EchoInDirection(Context context, IslandMap islandMap, Drone drone, Deque<Compass> direction)
    {
        this.context = context;
        this.islandMap = islandMap;
        this.drone = drone;
        this.direction = direction;
    }

    @Override
    public Decision takeDecision()
    {
        return new Echo(direction.peek());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        EchoResultat echoResultat = new EchoResultat(result);
        Compass heading = drone.getHeading();

        if (echoResultat.getFound() == Element.GROUND)
            return new StopExploration();

        islandMap.grow(direction.pop(), echoResultat.getRange());

        if (!direction.isEmpty())
            return new EchoInDirection(context, islandMap, drone, direction);

        int right = islandMap.getDimension(heading.get(Direction.RIGHT));
        int left = islandMap.getDimension(heading.get(Direction.LEFT));
        Compass newHeading = right > left ? heading.get(Direction.RIGHT) : heading.get(Direction.LEFT);
        return new Turn(new ScanMap(context, islandMap, drone, heading), islandMap, drone, newHeading);
    }
}
