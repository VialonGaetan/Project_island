package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import java.awt.Point;
import java.util.Map;

/**
 * @author romain
 * @since 15/02/2017.
 */
public interface Board
{
    void grow(Direction direction, int range);

    boolean isOnBoard(Point point);

    fr.unice.polytech.si3.qgl.iaad.future.Tile getTile(Point point);

    void zoom();

    Map<Point, fr.unice.polytech.si3.qgl.iaad.future.Tile> getMap();
}

