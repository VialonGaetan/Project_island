package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 04/03/2017.
 */
public class EchoCheck extends Aerial implements Protocol
{
    private final Protocol groundProtocol;
    private final Protocol outOfRangeProtocol;

    EchoCheck(Protocol groundProtocol, Protocol outOfRangeProtocol, Context context, IslandMap islandMap, Drone drone)
    {
        super(context, islandMap, drone);
        this.groundProtocol = groundProtocol;
        this.outOfRangeProtocol = outOfRangeProtocol;
    }

    @Override
    public Decision takeDecision()
    {
        return new Echo(drone.getHeading());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        EchoResult echoResult = new EchoResult(result);

        if (echoResult.getFound() == Element.OUT_OF_RANGE)
            return outOfRangeProtocol;
        return new FlyToIsland(groundProtocol, context, islandMap, drone, echoResult.getRange() + 1);
    }
}
