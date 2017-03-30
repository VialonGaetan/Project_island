package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * @since 25/03/2017.
 */
public class FlyOnMapTest
{
    private FlyOnMap flyOnMap;
    private IslandMap islandMap;

    /**
     * init a new fly on the map in the east direction with an empty map
     */
    @Before
    public void setUp()
    {
        islandMap = new IslandMap();
        flyOnMap = new FlyOnMap(null, islandMap, new Drone(Compass.E));
    }

    /**
     * test we can still fly on the map ie if the decision is an instance of Fly
     * if it cannot fly on the map anymore, then test if the decion is an instance of Stop
     */
    @Test
    public void takeDecision()
    {
        assertTrue(flyOnMap.takeDecision() instanceof Stop);
        islandMap.grow(Compass.E, 1);
        flyOnMap = new FlyOnMap(null, islandMap, new Drone(Compass.E));
        assertTrue(flyOnMap.takeDecision() instanceof Fly);
    }

    @Test
    public void acknowledgeResults()
    {
        assertNull(flyOnMap.acknowledgeResults(null));
    }
}
