package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class LandOnIslandTest
{
    private LandOnIsland landOnIsland;

    @Before
    public void setUp() throws Exception
    {
        IslandMap map = DroneTest.facticeMap();
        Point position = new Point(12, 0);
        landOnIsland = new LandOnIsland(map, position);
    }

    @Test
    public void nextAction() throws Exception
    {
        Area area = (Area) landOnIsland.nextAction();
        assertTrue(area instanceof Land);
        assertTrue(area.people > 0);
        assertEquals(DroneTest.CREEK, area.ID);
    }

    @Test
    public void setResult() throws Exception
    {
        Area area = ((Area) landOnIsland.nextAction()).putResults(DroneTest.createJSON(10).toString());
        assertTrue(landOnIsland.setResult(area) instanceof StopAerial);
    }

}