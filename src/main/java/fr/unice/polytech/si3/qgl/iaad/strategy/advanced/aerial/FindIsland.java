package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Turn;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Alexandre Clement
 * @since 02/03/2017.
 */
public class FindIsland extends Aerial implements Protocol
{
    private final Deque<Decision> decisions;

    FindIsland(Context context, IslandMap islandMap, Drone drone)
    {
        super(context, islandMap, drone);
        decisions = new ArrayDeque<>();
    }

    @Override
    public Decision takeDecision()
    {
        if (decisions.isEmpty())
            setupDecisions();
        return decisions.peek();
    }

    private void setupDecisions()
    {
        drone.heading(getLargestSide());
        if (!islandMap.isOnMap(drone.getLocation()))
            decisions.add(new Stop());
        decisions.add(new Heading(drone.getHeading()));
        decisions.add(new Echo(getLargestSide()));
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        decisions.pop();

        if (!decisions.isEmpty())
            return this;

        EchoResult echoResult = new EchoResult(result);

        if (echoResult.getFound() == Element.OUT_OF_RANGE)
            return new FindIsland(context, islandMap, drone);

        Protocol exit = new ScanIsland(context, islandMap, drone, drone.getHeading());
        if (echoResult.getRange() > 1)
            exit = new FlyToIsland(exit, context, islandMap, drone, echoResult.getRange() - 1);
        return new Turn(exit, islandMap, drone, getLargestSide());
    }
}
