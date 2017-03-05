package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
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