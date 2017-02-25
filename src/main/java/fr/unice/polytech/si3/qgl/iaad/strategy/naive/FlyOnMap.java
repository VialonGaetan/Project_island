package fr.unice.polytech.si3.qgl.iaad.strategy.naive;

import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.board.Board;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class FlyOnMap implements Protocol
{
    private final Protocol exit;
    private final Board board;
    private final Drone drone;

    FlyOnMap(Protocol exit, Board board, Drone drone)
    {
        this.exit = exit;
        this.board = board;
        this.drone = drone;
    }

    @Override
    public Decision takeDecision()
    {
        drone.fly();
        if (board.isOnBoard(drone.getLocation()))
            return new Fly();
        return new Stop();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return exit;
    }
}
