package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class InitialisationTest
{
    private Protocol initialisation;
    private Direction heading;
    private IslandMap map;

    @Before
    public void setUp() throws Exception
    {
        map = new IslandMap();
        heading = Direction.E;
        initialisation = new Initialisation(map, heading);
    }

    @Test
    public void nextAction() throws Exception
    {
        int[] mapSize = new int[]{20, 0, 10};

        for (int i = 0; i < 3; i++)
        {
            Area area = (Area) initialisation.nextAction();
            assertTrue(area instanceof Echo);
            assertNotEquals(heading.getBack(), area.direction);
            area.putResults(DroneTest.createEchoJSON(10, mapSize[i], Element.OUT_OF_RANGE.toString()).toString());
            initialisation = initialisation.setResult(area);
        }

        assertEquals(20, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.S));
    }

    @Test
    public void setResult() throws Exception
    {
        Area area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof Initialisation.EchoToFindLimit);

        area.putResults(DroneTest.createEchoJSON(10, 12, Element.GROUND.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof FlyToIsland);
    }

    @Test
    public void groundNotFound() throws Exception
    {
        Area area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof Initialisation.EchoToFindLimit);

        area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof Initialisation.EchoToFindLimit);

        area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof FindIsland);

    }
}