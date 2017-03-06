package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Alexandre Clement
 * @since 03/03/2017.
 */
public class FlyToIsland extends Aerial implements Protocol
{
    private final Deque<Protocol> protocols;
    private final Protocol exit;

    FlyToIsland(Protocol exit, Context context, IslandMap islandMap, Drone drone, int range)
    {
        super(context, islandMap, drone);
        this.exit = exit;
        protocols = new ArrayDeque<>();
        for (int i = 0; i < range; i++)
            protocols.add(new FlyOnMap(null, islandMap, drone));
    }

    @Override
    public Decision takeDecision()
    {
        return protocols.peek().takeDecision();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        protocols.pop();
        if (protocols.isEmpty())
            return exit;
        return this;
    }
}
