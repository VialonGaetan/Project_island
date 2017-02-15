package fr.unice.polytech.si3.qgl.iaad;

import java.awt.*;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */
public enum Direction {
    N(new Point(0, -1)),
    E(new Point(1, 0)),
    S(new Point(0, 1)),
    W(new Point(-1, 0)),
    SE(new Point(1, 1)),
    NE(new Point(1, -1)),
    NW(new Point(-1, -1)),
    SW(new Point(-1, 1));

    private Point vecteur;

    Direction(Point vecteur)
    {
        this.vecteur = vecteur;
    }

    public Point getVecteur()
    {
        return new Point(vecteur);
    }

    public Direction getRight()
    {
        return Direction.values()[(this.ordinal() + 1) % 4];
    }

    public Direction getLeft()
    {
        return Direction.values()[(this.ordinal() + 3) % 4];
    }

    public Direction getBack()
    {
        return Direction.values()[(this.ordinal() + 2) % 4];
    }

}
