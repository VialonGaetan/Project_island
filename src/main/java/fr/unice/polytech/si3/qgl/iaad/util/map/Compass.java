package fr.unice.polytech.si3.qgl.iaad.util.map;

import java.awt.*;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */
public enum Compass
{
    N(new Point(0, -1)),
    E(new Point(1, 0)),
    S(new Point(0, 1)),
    W(new Point(-1, 0));

    private Point vector;

    Compass(Point vector)
    {
        this.vector = vector;
    }

    public Point getVector()
    {
        return new Point(vector);
    }

    public Compass get(Direction direction)
    {
        return Compass.values()[(this.ordinal() + direction.ordinal()) % 4];
    }

    public boolean isOrthogonal(Compass compass)
    {
        return get(Direction.LEFT) == compass || get(Direction.RIGHT) == compass;
    }
}
