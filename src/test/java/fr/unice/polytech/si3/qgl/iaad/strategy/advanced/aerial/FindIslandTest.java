package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 20/03/17.
 */
public class FindIslandTest
{
    private FindIsland findIsland;
    private Drone drone;
    private IslandMap islandMap;

    @Before
    public void setUp()
    {
        drone = new Drone(Compass.E);
        islandMap = new IslandMap();
        findIsland = new FindIsland(null, islandMap, drone);
    }

    @Test
    public void takeStopDecision()
    {
        assertTrue(findIsland.takeDecision() instanceof Stop);
        islandMap.grow(Compass.E, 2);
        findIsland = new FindIsland(null, islandMap, drone);
        assertTrue(findIsland.takeDecision() instanceof Stop);
        islandMap = new IslandMap();
        islandMap.grow(Compass.N, 3);
        findIsland = new FindIsland(null, islandMap, drone);
        assertTrue(findIsland.takeDecision() instanceof Stop);
        islandMap.grow(Compass.E, 2);
        findIsland = new FindIsland(null, islandMap, drone);
        assertTrue(findIsland.takeDecision() instanceof Stop);
    }

    @Test
    public void takeHeadingDecision()
    {
        for(Compass compass : Compass.values())
        {
            islandMap.grow(compass, 10);
        }

        findIsland = new FindIsland(null, islandMap, drone);
        assertTrue(findIsland.takeDecision() instanceof Heading);
    }
}
