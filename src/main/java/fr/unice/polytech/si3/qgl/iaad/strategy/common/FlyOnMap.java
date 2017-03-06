package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
public class FlyOnMap implements Protocol
{
    private final Protocol exit;
    private final IslandMap islandMap;
    private final Drone drone;

    public FlyOnMap(Protocol exit, IslandMap islandMap, Drone drone)
    {
        this.exit = exit;
        this.islandMap = islandMap;
        this.drone = drone;
    }

    @Override
    public Decision takeDecision()
    {
        drone.fly();
        if (islandMap.isOnMap(drone.getLocation()))
            return new Fly();
        return new Stop();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return exit;
    }
}
