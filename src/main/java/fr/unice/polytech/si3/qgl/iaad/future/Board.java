package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Board
{
    void grow(Direction direction, int range);

    boolean isOnBoard(Point point);

    fr.unice.polytech.si3.qgl.iaad.islandMap.Tile getTile(Point point);
}
