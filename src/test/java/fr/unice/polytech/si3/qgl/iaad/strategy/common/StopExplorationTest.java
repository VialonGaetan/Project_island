package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * @since 25/03/2017.
 */
public class StopExplorationTest
{
    @Test
    public void takeDecision()
    {
        assertTrue(new StopExploration().takeDecision() instanceof Stop);
    }

    @Test
    public void acknowledgeResults()
    {
        assertNull(new StopExploration().acknowledgeResults(null));
    }
}
