package fr.unice.polytech.si3.qgl.iaad.strategy.naive;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.board.Board;
import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class NaiveStrategy implements Protocol
{
    private final Protocol initialisation;

    public NaiveStrategy(Context context)
    {
        Direction heading = context.getHeading();
        initialisation = new EchoInDirection(context, new Board(), new Drone(heading), heading, heading.getRight(), heading.getLeft());
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
