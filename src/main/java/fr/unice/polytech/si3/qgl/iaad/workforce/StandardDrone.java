package fr.unice.polytech.si3.qgl.iaad.workforce;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class StandardDrone implements Drone
{
    private final Point location;
    private Direction heading;

    public StandardDrone(Direction heading)
    {
        this.heading = heading;
        location = new Point();
    }

    @Override
    public Point getLocation()
    {
        return new Point(location);
    }

    @Override
    public Direction getHeading()
    {
        return heading;
    }

    @Override
    public void fly()
    {
        location.translate(heading.getVecteur().x, heading.getVecteur().y);
    }

    @Override
    public void heading(Direction direction)
    {
        fly();
        heading = direction;
        fly();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        StandardDrone that = (StandardDrone) o;

        return location.equals(that.location) && heading == that.heading;
    }

    @Override
    public int hashCode()
    {
        int result = location.hashCode();
        result = 31 * result + heading.hashCode();
        return result;
    }
}
