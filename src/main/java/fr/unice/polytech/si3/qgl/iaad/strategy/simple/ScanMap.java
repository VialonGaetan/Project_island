package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.util.map.Board;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.protocol.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.player.results.ScanResultat;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class ScanMap implements Protocol
{
    private final Context context;
    private final Board board;
    private final Drone drone;
    private final Direction sense;

    ScanMap(Context context, Board board, Drone drone, Direction sense)
    {
        this.context = context;
        this.board = board;
        this.drone = drone;
        this.sense = sense;
    }

    @Override
    public Decision takeDecision()
    {
        return new Scan();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        ScanResultat scanResultat = new ScanResultat(result);
        Direction heading = drone.getHeading();

        if (!scanResultat.getCreeks().isEmpty())
        {
            // Remplacer "StopExploration" par la stratégie terrestre.
            Protocol groundStrategy = new StopExploration();
            return new LandOnCreek(groundStrategy, scanResultat.getCreeks().get(0), context.getNumberOfMen() - 1);
        }

        if (droneIsAbleToFlyInDirection(heading))
            return new FlyOnMap(this, board, drone);

        if (droneIsAbleToFlyInDirection(sense))
            return new Turn(new Turn(this, board, drone, heading.getBack()), board, drone, sense);

        Protocol exit = new ScanMap(context, board, drone, sense.getBack());
        exit = new Turn(exit, board, drone, heading.getBack());
        exit = new FlyOnMap(exit, board, drone);
        return new Turn(exit, board, drone, sense.getBack());
    }

    private boolean droneIsAbleToFlyInDirection(Direction direction)
    {
        Point location = drone.getLocation();
        Point vector = direction.getVecteur();
        location.translate(vector.x, vector.y);
        location.translate(vector.x, vector.y);
        return board.isOnBoard(location);
    }
}
