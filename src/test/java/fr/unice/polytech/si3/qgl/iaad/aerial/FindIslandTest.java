package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class FindIslandTest
{
    private Protocol findIsland;
    private IslandMap map;
    private Direction heading;

    @Test
    public void nextAction() throws Exception
    {
        for (int i = 0; i < 10000; i++)
        {
            map = DroneTest.facticeMap();
            heading = Direction.S;
            findIsland = new FindIsland(map, heading, null);
            Point position;
            while (findIsland instanceof FindIsland)
            {
                position = map.getLocation();
                Area area = (Area) findIsland.nextAction();
                if (area instanceof Echo || area instanceof Heading)
                {
                    assertNotNull(area.direction);
                    assertNotEquals(heading.getBack(), area.direction);
                }
                if (area instanceof Fly)
                {
                    position.translate(heading.getVecteur().x, heading.getVecteur().y);
                    assertEquals(position, map.getLocation());
                }
                if (area instanceof Heading)
                {
                    assertTrue(map.getNumberOfAvailablePoints(area.direction) > 1);
                }
                findIsland = findIsland.setResult(area.putResults(DroneTest.facticeJSON().toString()));
            }
        }
    }

    @Test
    public void setResult() throws Exception
    {
        for (int i = 0; i < 10000; i++)
        {
            map = DroneTest.facticeMap();
            heading = Direction.S;
            findIsland = new FindIsland(map, heading, null);
            while (findIsland instanceof FindIsland)
            {
                Area area = (Area) findIsland.nextAction();
                findIsland = findIsland.setResult(area.putResults(DroneTest.facticeJSON().toString()));
                assertNotNull(findIsland);
                assertTrue(findIsland instanceof FindIsland || findIsland instanceof FlyToIsland);
            }
        }
    }
}