package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class Drone
{
    private final Point location;
    private Compass heading;

    public Drone(Compass heading)
    {
        this.heading = heading;
        location = new Point();
    }

    public Drone(Drone drone)
    {
        this.heading = drone.getHeading();
        this.location = new Point(drone.getLocation());
    }

    public Point getLocation()
    {
        return new Point(location);
    }

    public Compass getHeading()
    {
        return heading;
    }

    public void fly()
    {
        location.translate(heading.getVector().x, heading.getVector().y);
    }

    public void heading(Compass direction)
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

        Drone that = (Drone) o;

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
