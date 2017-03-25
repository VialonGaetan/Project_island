package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandre Clement
 * @author romain
 * @since 05/03/2017.
 */
public class AerialTest
{
    private IslandMap islandMap;
    private Drone drone;
    private Aerial aerial;

    @Before
    public void setUp() throws Exception
    {
        islandMap = new IslandMap();
        drone = new Drone(Compass.E);
        aerial = new Aerial(null, islandMap, drone);
    }

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