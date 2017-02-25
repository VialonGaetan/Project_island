package fr.unice.polytech.si3.qgl.iaad.strategy.naive;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.board.Board;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class Turn implements Protocol
{
    private final Protocol exit;
    private final Board board;
    private final Drone drone;
    private final Direction direction;

    Turn(Protocol exit, Board board, Drone drone, Direction direction)
    {
        this.exit = exit;
        this.board = board;
        this.drone = drone;
        this.direction = direction;
    }

    @Override
    public Decision takeDecision()
    {
        drone.heading(direction);
        if (!board.isOnBoard(drone.getLocation()))
            return new Stop();
        return new Heading(direction);
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return exit;
    }
}
