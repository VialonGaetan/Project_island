package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 20/03/17.
 */
public class ScanIslandTest
{
    private ScanIsland scanIsland;
    private IslandMap islandMap;
    private Drone drone;

    @Before
    public void setUp()
    {
        islandMap = new IslandMap();
        drone = new Drone(Compass.E);
        scanIsland = new ScanIsland(null, islandMap, drone, Compass.E);
    }

    @Test
    public void takeDecision()
    {
        assertTrue(scanIsland.takeDecision() instanceof Scan);
    }
}
