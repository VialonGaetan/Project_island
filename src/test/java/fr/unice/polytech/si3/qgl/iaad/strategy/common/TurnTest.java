package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Heading;
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
    private Turn turn;
    private IslandMap islandMap;

    @Before
    public void setUp()
    {
        islandMap = new IslandMap();
        turn = new Turn(null, islandMap, new Drone(Compass.E), Compass.E);
    }

    @Test
    public void takeDecision()
    {
        assertTrue(turn.takeDecision() instanceof Stop);
        islandMap.grow(Compass.E, 2);
        turn = new Turn(null, islandMap, new Drone(Compass.E), Compass.E);
        assertTrue(turn.takeDecision() instanceof Heading);
    }

    @Test
    public void acknowledgeResults()
    {
        assertNull(turn.acknowledgeResults(null));
    }
}
