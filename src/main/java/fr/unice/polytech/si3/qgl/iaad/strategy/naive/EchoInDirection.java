package fr.unice.polytech.si3.qgl.iaad.strategy.naive;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.board.Board;
import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.protocol.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.resultats.EchoResultat;
import fr.unice.polytech.si3.qgl.iaad.workforce.Drone;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class EchoInDirection implements Protocol
{
    private final Context context;
    private final Board board;
    private final Drone drone;
    private final Deque<Direction> direction;

    EchoInDirection(Context context, Board board, Drone drone, Direction... direction)
    {
        this(context, board, drone, new ArrayDeque<>(Arrays.asList(direction)));
    }

    private EchoInDirection(Context context, Board board, Drone drone, Deque<Direction> direction)
    {
        this.context = context;
        this.board = board;
        this.drone = drone;
        this.direction = direction;
    }

    @Override
    public Decision takeDecision()
    {
        return new Echo(direction.peek());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        EchoResultat echoResultat = new EchoResultat(result);
        Direction heading = drone.getHeading();

        if (echoResultat.getFound() == Element.GROUND)
            return new StopExploration();

        board.grow(direction.pop(), echoResultat.getRange());

        if (!direction.isEmpty())
            return new EchoInDirection(context, board, drone, direction);

        int right = board.getDimension(heading.getRight());
        int left = board.getDimension(heading.getLeft());
        Direction newHeading = right > left ? heading.getRight() : heading.getLeft();
        return new Turn(new ScanMap(context, board, drone, heading), board, drone, newHeading);
    }
}
