package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 20/03/17.
 */
public class FlyToIslandTest
{
    private FlyToIsland flyToIsland;
    private Drone drone;
    private IslandMap islandMap;

    @Before
    public void setUp()
    {
        drone = new Drone(Compass.E);
        islandMap = new IslandMap();
        islandMap.grow(Compass.E, 2);
        flyToIsland = new FlyToIsland(null, null, islandMap, drone, 2);
    }

    @Test
    public void flyToIsland()
    {
        assertTrue(flyToIsland.takeDecision() instanceof Fly);
        assertTrue(flyToIsland.acknowledgeResults(null) instanceof FlyToIsland);
        assertTrue(flyToIsland.takeDecision() instanceof Fly);
        assertTrue(flyToIsland.takeDecision() instanceof Stop);
    }
}
