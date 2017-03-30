package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author romain
 * @author Alexandre Clement
 * @since 05/03/2017.
 */
public class AerialTest
{
    private IslandMap islandMap;
    private Drone drone;
    private Aerial aerial;

    /**
     * create an empty map
     * give an init heading to the drone (right here is East)
     * init the object aerial
     * @throws Exception, if there is a problem of init
     */
    @Before
    public void setUp() throws Exception
    {
        islandMap = new IslandMap();
        drone = new Drone(Compass.E);
        aerial = new Aerial(null, islandMap, drone);
    }

    /**
     * test if the drone can fly with existing points on the map
     * it depends of the dimensions of the map ...
     */
    @Test
    public void droneIsAbleToFly()
    {
        for(Compass compass : Compass.values())
        {
            drone = new Drone(compass);
            islandMap = new IslandMap();
            aerial = new Aerial(null, islandMap, drone);
            assertFalse(aerial.droneIsAbleToFly());
            islandMap.grow(compass, 1);
            aerial = new Aerial(null, islandMap, drone);
            assertFalse(aerial.droneIsAbleToFly());
            islandMap.grow(compass, 1);
            aerial = new Aerial(null, islandMap, drone);
            assertTrue(aerial.droneIsAbleToFly());
        }
    }

    /**
     * test if the drone can turn to a direction, it's similar to droneIsAbleToFly test
     */
    @Test
    public void droneIsAbleToTurnInDirection()
    {
        for(Compass compass : Compass.values())
        {
            drone = new Drone(compass);
            islandMap = new IslandMap();
            aerial = new Aerial(null, islandMap, drone);
            assertFalse(aerial.droneIsAbleToTurnInDirection(compass));
            islandMap.grow(compass, 1);
            aerial = new Aerial(null, islandMap, drone);
            assertFalse(aerial.droneIsAbleToTurnInDirection(compass));
            islandMap.grow(compass.get(Direction.LEFT), 2);
            aerial = new Aerial(null, islandMap, drone);
            assertTrue(aerial.droneIsAbleToTurnInDirection(compass.get(Direction.LEFT)));
        }
    }

    /**
     * almost the same as the previous method
     */
    @Test
    public void droneIsAbleToUTurnInDirection()
    {
        for(Compass compass : Compass.values())
        {
            drone = new Drone(compass);
            islandMap = new IslandMap();
            aerial = new Aerial(null, islandMap, drone);
            assertFalse(aerial.droneIsAbleToUTurnInDirection(compass));
            islandMap.grow(compass, 1);
            aerial = new Aerial(null, islandMap, drone);
            assertTrue(aerial.droneIsAbleToUTurnInDirection(compass));
            islandMap.grow(compass.get(Direction.LEFT), 2);
            aerial = new Aerial(null, islandMap, drone);
            assertFalse(aerial.droneIsAbleToUTurnInDirection(compass.get(Direction.LEFT)));
        }
    }

    /**
     * test if a creek is correctly found on the map
     */
    @Test
    public void findCreekLocation()
    {
        List<Creek> creeks = new ArrayList<>();
        islandMap.getTile(new Point()).addCreeks(new ArrayList<>());
        aerial = new Aerial(null, islandMap, drone);
        assertEquals(Optional.empty(), aerial.findCreekLocation());
        creeks.add(new Creek("1"));
        islandMap.getTile(new Point()).addCreeks(creeks);
        aerial = new Aerial(null, islandMap, drone);
        assertEquals(Optional.of(new Point()), aerial.findCreekLocation());
    }

    /**
     * get the largest orthogonal side
     */
    @Test
    public void getLargestSide()
    {
        islandMap.grow(Compass.E, 2);
        aerial = new Aerial(null, islandMap, drone);
        assertEquals(Compass.N, aerial.getLargestSide());
        islandMap.grow(Compass.E, 5);
        aerial = new Aerial(null, islandMap, drone);
        assertEquals(Compass.N, aerial.getLargestSide());
        islandMap.grow(Compass.W, 2);
        aerial = new Aerial(null, islandMap, drone);
        assertEquals(Compass.N, aerial.getLargestSide());
        islandMap.grow(Compass.S, 2);
        aerial = new Aerial(null, islandMap, drone);
        assertEquals(Compass.S, aerial.getLargestSide());
    }

    /**
     * the coordinates of the point if there is a zoom and a land
     */
    @Test
    public void aerialToGroundLocation()
    {
        Point point = new Point();
        assertEquals(new Point(), aerial.aerialToGroundLocation(point));
        point = new Point(3, 5);
        assertEquals(new Point(9, 15), aerial.aerialToGroundLocation(point));
    }

    @Test
    public void distanceToCenter() throws Exception
    {
        islandMap.grow(Compass.E, 50);
        assertEquals(0, aerial.distanceToCenter(Compass.E));
        drone.fly();
        assertEquals(1, aerial.distanceToCenter(Compass.E));

        islandMap.grow(Compass.N, 10);
        islandMap.grow(Compass.S, 20);

        assertEquals(1, aerial.distanceToCenter(Compass.E));
        drone.heading(Compass.S);
        assertEquals(2, aerial.distanceToCenter(Compass.E));


        drone.heading(Compass.E);
        assertEquals(3, aerial.distanceToCenter(Compass.E));
        drone.fly();
        assertEquals(4, aerial.distanceToCenter(Compass.E));
    }

    /**
     * test the distance between the current drone location and the limit in such direction
     * @throws Exception
     */
    @Test
    public void distanceToLimit() throws Exception
    {
        islandMap.grow(Compass.E, 50);
        assertEquals(50, aerial.distanceToLimit(Compass.E));
        drone.fly();
        assertEquals(49, aerial.distanceToLimit(Compass.E));

        islandMap.grow(Compass.E, 2);

        assertEquals(51, aerial.distanceToLimit(Compass.E));
        drone.fly();
        assertEquals(50, aerial.distanceToLimit(Compass.E));
    }
}