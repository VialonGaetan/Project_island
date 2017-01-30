package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class ScanIslandTest
{
    private Protocol scanIsland;
    private Direction direction;
    private IslandMap map;

    @Test
    public void nextAction() throws Exception
    {
        for (int i = 0; i < DroneTest.TEST; i++)
        {
            map = DroneTest.randomMap();
            direction = DroneTest.randomDirection();
            scanIsland = new ScanIsland(map, direction, direction.getRight());
            while (scanIsland instanceof ScanIsland || scanIsland instanceof FlyOnIsland || scanIsland instanceof ScanToFindCreekAndSite)
            {
                Area area = (Area) scanIsland.nextAction();
                area = area.putResults(DroneTest.randomJSON().toString());
                scanIsland = scanIsland.setResult(area);
                assertTrue(area instanceof Scan || area instanceof Fly);
                if (area instanceof Fly)
                {
                    Oriented oriented = (Oriented) scanIsland;
                    assertNotNull(oriented.getHeading());
                    assertNotEquals(oriented.getHeading().getBack(), area.direction);
                    assertTrue(map.getNumberOfAvailablePoints(oriented.getHeading()) >= 0);
                }
            }
        }
    }



    @Test
    public void setResult() throws Exception
    {
        for (int i = 0; i < DroneTest.TEST; i++)
        {
            map = DroneTest.randomMap();
            direction = DroneTest.randomDirection();
            scanIsland = new ScanIsland(map, direction, direction.getRight());
            while (scanIsland instanceof ScanIsland || scanIsland instanceof FlyOnIsland || scanIsland instanceof ScanToFindCreekAndSite)
            {
                Area area = (Area) scanIsland.nextAction();
                area = area.putResults(DroneTest.randomJSON().toString());
                scanIsland = scanIsland.setResult(area);
            }
            assertTrue(scanIsland instanceof StopAerial || scanIsland instanceof ReturnToIsland);
        }
    }
}