package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
class Oriented
{
    /**
     * La carte utilisée
     */
    private final IslandMap map;
    /**
     * L'orientation du drone
     */
    private Direction heading;
    /**
     * Conservation du sens de parcours de la carte pour l'exploration de l'île
     */
    private Direction sense;
    /**
     * Direction pour une fr.unice.polytech.si3.qgl.iaad.action i.e direction d'un ECHO, HEADING.
     */
    private Direction direction;

    public Oriented(IslandMap map, Direction heading)
    {
        this.map = map;
        this.heading = heading;
    }

    Oriented(IslandMap map, Direction heading, Direction sense)
    {
        this(map, heading);
        this.sense = sense;
    }

    Oriented(IslandMap map, Direction heading, Direction sense, Direction direction)
    {
        this(map, heading, sense);
        this.direction = direction;
    }

    public IslandMap getMap()
    {
        return map;
    }

    public Direction getHeading()
    {
        return heading;
    }

    public void setHeading(Direction heading)
    {
        this.heading = heading;
    }

    public Direction getSense()
    {
        return sense;
    }

    public void setSense(Direction sense)
    {
        this.sense = sense;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
}
