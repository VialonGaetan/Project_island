package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.EchoResultat;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Turn;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 04/03/2017.
 */
public class AlongCoasts extends Aerial implements Protocol
{
    private final Protocol exit;
    private final Compass direction;

    AlongCoasts(Protocol exit, Context context, IslandMap islandMap, Drone drone, Compass direction)
    {
        super(context, islandMap, drone);
        this.exit = exit;
        this.direction = direction;
    }

    @Override
    public Decision takeDecision()
    {
        return new Echo(direction);
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        EchoResultat echoResultat = new EchoResultat(result);

        if (echoResultat.getFound() == Element.GROUND && echoResultat.getRange() <= 2)
            return new FlyOnMap(this, islandMap, drone);

        Protocol protocol = new Turn(exit, islandMap, drone, drone.getHeading().get(Direction.BACK));
        return new Turn(protocol, islandMap, drone, direction);
    }
}
