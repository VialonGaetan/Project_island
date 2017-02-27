package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.util.map.Board;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.protocol.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.player.results.EchoResultat;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

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
