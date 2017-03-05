
package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Turn;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 04/03/2017.
 */
public class ReturnToIsland extends Aerial implements Protocol
{
    private Protocol protocol;

    ReturnToIsland(Context context, IslandMap islandMap, Drone drone, Compass direction)
    {
        super(context, islandMap, drone);

        Protocol groundProtocol;
        Protocol outOfRangeProtocol;

        groundProtocol = new ScanIsland(context, islandMap, drone, direction);

        outOfRangeProtocol = new ScanIsland(context, islandMap, drone, direction.get(Direction.BACK));
        outOfRangeProtocol = new Turn(outOfRangeProtocol, islandMap, drone, drone.getHeading().get(Direction.BACK));
        outOfRangeProtocol = new FlyOnMap(outOfRangeProtocol, islandMap, drone);
        outOfRangeProtocol = new Turn(outOfRangeProtocol, islandMap, drone, direction.get(Direction.BACK));

        Protocol afterFlyingAlongTheCoasts = new EchoCheck(groundProtocol, outOfRangeProtocol, context, islandMap, drone);

        groundProtocol = new ScanIsland(context, islandMap, drone, direction);
        outOfRangeProtocol = new AlongCoasts(afterFlyingAlongTheCoasts, context, islandMap, drone, direction);
        protocol = new EchoCheck(groundProtocol, outOfRangeProtocol, context, islandMap, drone);
    }

    @Override
    public Decision takeDecision()
    {
        return protocol.takeDecision();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return protocol.acknowledgeResults(result);
    }
}
