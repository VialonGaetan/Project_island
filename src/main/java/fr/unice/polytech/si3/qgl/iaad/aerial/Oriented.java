package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
class Oriented
{
    protected Direction direction;

    Oriented(Direction direction)
    {
        this.direction = direction;
    }

    public Direction getDirection()
    {
        return direction;
    }
}
