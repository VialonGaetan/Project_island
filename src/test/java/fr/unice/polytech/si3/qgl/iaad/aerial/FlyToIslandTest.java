package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class FlyToIslandTest
{
    private FlyToIsland flyToIslandWithTurn;
    private FlyToIsland flyToIsland;
    private FlyToIsland fail;
    private IslandMap map;
    private Direction target;
    private int range;

    @Before
    public void setUp() throws Exception
    {
        map = DroneTest.facticeMap();
        Direction heading = Direction.E;
        target = Direction.S;
        Direction failTarget = Direction.N;
        range = 10;
        flyToIsland = new FlyToIsland(map, heading, heading, null, range);
        flyToIslandWithTurn = new FlyToIsland(map, heading, target, null, range);
        fail = new FlyToIsland(map, heading, failTarget, null, range);

    }

    @Test
    public void flyToIsland() throws Exception
    {
        Point position = new Point(map.getLocation());
        position.translate(11, 0);
        flyRange(flyToIsland, range);
        assertEquals(position, map.getLocation());
    }

    @Test
    public void flyToIslandWithTurn() throws Exception
    {
        Point position = new Point(map.getLocation());
        position.translate(1, 11);

        Area area = (Area) flyToIslandWithTurn.nextAction();
        assertTrue(area instanceof Heading);
        assertEquals(target, area.direction);
        area.putResults(DroneTest.createJSON(10).toString());
        assertEquals(flyToIsland, flyToIsland.setResult(area));

        flyRange(flyToIslandWithTurn, range - 1);

        assertEquals(position, map.getLocation());
    }

    @Test
    public void flyToIslandFailed() throws Exception
    {
        assertTrue(fail.nextAction() instanceof Stop);
    }

    private void flyRange(FlyToIsland fly, int range) throws InvalidMapException
    {
        Area area;
        for (int i = 0; i < range; i++)
        {
            area = (Area) fly.nextAction();
            assertTrue(area instanceof Fly);
            area.putResults(DroneTest.createJSON(10).toString());
            assertEquals(fly, fly.setResult(area));
        }
        area = (Area) fly.nextAction();
        assertTrue(area instanceof Fly);
        area.putResults(DroneTest.createJSON(10).toString());
        assertNotEquals(fly, fly.setResult(area));
    }

}