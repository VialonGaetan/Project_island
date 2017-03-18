package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.simple.aerial.EchoInDirection;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class StrategySimple implements Protocol
{
    private final Protocol initialisation;

    public StrategySimple(Context context)
    {
        Compass heading = context.getHeading();
        initialisation = new EchoInDirection(context, new IslandMap(), new Drone(heading), heading, heading.get(Direction.RIGHT), heading.get(Direction.LEFT));
    }

    @Override
    public Decision takeDecision()
    {
        return initialisation.takeDecision();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return initialisation.acknowledgeResults(result);
    }
}
