package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author romain
 * @since 25/03/2017.
 */
public class LandOnCreekTest
{
    private LandOnCreek landOnCreek;
    private IslandMap islandMap;
    private Point creekLocation;
    private Crew crew;

    /**
     * creates a map with a creek
     * create a crew
     */
    @Before
    public void setUp()
    {
        islandMap = new IslandMap();
        crew = new Crew(100, new Point());
        creekLocation = new Point();
        List<Creek> creeks = new ArrayList<>();
        creeks.add(new Creek("id"));
        islandMap.getTile(creekLocation).addCreeks(creeks);
        landOnCreek = new LandOnCreek(null, islandMap, creekLocation, crew);
    }

    /**
     * test if we can land on the creek (ie if the decision is an instance of land)
     */
    @Test
    public void takeDecision()
    {
        assertTrue(landOnCreek.takeDecision() instanceof Land);
        Land land = new Land(islandMap.getTile(creekLocation).getCreeks().get(0).getId(), crew.getPeople());
        assertEquals(100, land.getPeople());
        assertEquals("id", land.getID());
        assertEquals(ArgActions.LAND, land.getActionType());
    }

    @Test
    public void acknowledgeResults()
    {
        assertNull(landOnCreek.acknowledgeResults(null));
    }
}
