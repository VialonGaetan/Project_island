package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class Turn implements Protocol
{
    private final Protocol exit;
    private final IslandMap islandMap;
    private final Drone drone;
    private final Direction direction;

    Turn(Protocol exit, IslandMap islandMap, Drone drone, Direction direction)
    {
        this.exit = exit;
        this.islandMap = islandMap;
        this.drone = drone;
        this.direction = direction;
    }

    @Override
    public Decision takeDecision()
    {
        drone.heading(direction);
        if (!islandMap.isOnBoard(drone.getLocation()))
            return new Stop();
        return new Heading(direction);
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return exit;
    }
}