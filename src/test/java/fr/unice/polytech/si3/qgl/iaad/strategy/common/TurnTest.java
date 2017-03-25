package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author romain
 * @since 25/03/2017.
 */
public class TurnTest
{
    private FlyOnMap flyOnMap;
    private IslandMap islandMap;
    private Drone drone;

    @Before
    public void setUp()
    {
        islandMap = new IslandMap();
        drone = new Drone(Compass.E);
        flyOnMap = new FlyOnMap(null, islandMap, drone);
    }

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
