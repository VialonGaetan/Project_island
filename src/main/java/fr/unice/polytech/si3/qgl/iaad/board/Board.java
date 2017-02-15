package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author romain
 * @since 15/02/2017.
 */
public interface Board
{
    void grow(Direction direction, int range);

    boolean isOnBoard(Point point);

    Tile getTile(Point point);
}

