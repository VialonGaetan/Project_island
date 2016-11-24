package fr.unice.polytech.si3.qgl.iaad;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */
public enum Direction {
    N,
    E,
    S,
    W;

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
