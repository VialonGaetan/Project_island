package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.Board;
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
