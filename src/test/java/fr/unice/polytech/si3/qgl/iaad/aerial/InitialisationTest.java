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
        for (int i = 0; i < DroneTest.TEST; i++)
        {
            map = new IslandMap();
            heading = Direction.E;
            initialisation = new Initialisation(map, heading);
            int range;
            boolean ground;
            int[] ranges = new int[4];
            int[] grounds = new int[4];
            while (initialisation instanceof Initialisation || initialisation instanceof EchoToFindLimit)
            {
                Area area = (Area) initialisation.nextAction();
                assertTrue(area instanceof Echo);
                assertNotEquals(heading.getBack(), area.direction);
                ground = Math.random() > 0.9;
                range = (int) (Math.random() * 40);

                if (ground)
                    grounds[area.direction.ordinal()] = range + 1;
                else
                    ranges[area.direction.ordinal()] = range;

                area.putResults(DroneTest.createEchoJSON((int) (Math.random() * 40), range, (ground ? Element.GROUND : Element.OUT_OF_RANGE).toString()).toString());
                initialisation = initialisation.setResult(area);
            }
            for (int j = 0; j < 4; j++)
            {
                assertEquals(Math.max(ranges[j], grounds[j]), map.getNumberOfAvailablePoints(Direction.values()[j]));
            }
        }
    }

    @Test
    public void setResult() throws Exception
    {
        Area area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof EchoToFindLimit);

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
        assertTrue(initialisation instanceof EchoToFindLimit);

        area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof EchoToFindLimit);

        area = (Area) initialisation.nextAction();
        area.putResults(DroneTest.createEchoJSON(10, 20, Element.OUT_OF_RANGE.toString()).toString());
        initialisation = initialisation.setResult(area);
        assertTrue(initialisation instanceof FindIsland);

    }
}