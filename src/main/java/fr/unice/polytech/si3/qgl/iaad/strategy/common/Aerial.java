package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 02/03/2017.
 */
public class Aerial
{
    protected final Context context;
    protected final IslandMap islandMap;
    protected final Drone drone;

    protected Aerial(Context context, IslandMap islandMap, Drone drone)
    {
        this.context = context;
        this.islandMap = islandMap;
        this.drone = drone;
    }

    protected boolean droneIsAbleToFly()
    {
        Drone simulation = new Drone(drone);
        simulation.fly();
        simulation.fly();
        return islandMap.isOnBoard(simulation.getLocation());
    }

    protected boolean droneIsAbleToTurnInDirection(Compass direction)
    {
        Drone simulation = new Drone(drone);
        simulation.heading(direction);
        simulation.fly();
        return islandMap.isOnBoard(simulation.getLocation());
    }

    protected boolean droneIsAbleToUTurnInDirection(Compass direction)
    {
        Drone simulation = new Drone(drone);
        simulation.heading(direction);
        simulation.heading(drone.getHeading().get(Direction.BACK));
        simulation.fly();
        return islandMap.isOnBoard(simulation.getLocation());
    }

    protected int distanceToCenter(Compass direction)
    {
        Point location = drone.getLocation();
        Point vector = direction.getVector();
        return Math.abs(matrixProduct(location, vector));
    }

    protected int distanceToLimit(Compass direction)
    {
        Point vector = direction.getVector();
        int range = islandMap.getDimension(direction);
        return Math.abs(scalarProduct(vector, range) - distanceToCenter(direction));
    }

    protected Compass getLargestSide()
    {
        int right = distanceToLimit(drone.getHeading().get(Direction.RIGHT));
        int left = distanceToLimit(drone.getHeading().get(Direction.LEFT));
        return drone.getHeading().get(right > left ? Direction.RIGHT : Direction.LEFT);
    }

    private int matrixProduct(Point point1, Point point2)
    {
        return point1.x * point2.x + point1.y * point2.y;
    }

    private int scalarProduct(Point point, int scalar)
    {
        return point.x * scalar + point.y * scalar;
    }
}
