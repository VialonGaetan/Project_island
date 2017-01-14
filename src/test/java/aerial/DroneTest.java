package aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 14/01/2017.
 */
public class DroneTest
{
    private Drone drone;
    private IslandMap islandMap;

    /**
     * create a new drone
     */
    @Before
    public void before()
    {
        islandMap = new IslandMap();
        drone = new Drone(100, Direction.E, islandMap);
    }

    /**
     * test init conditions
     */
    @Test
    public void Drone() throws InvalidMapException
    {
        assertEquals(100, drone.getBudget());
        assertTrue(islandMap.isDirectionFinished(Direction.W));
        assertFalse(islandMap.isFinished());
        assertEquals(1, islandMap.getHorizontalDimension());
        assertEquals(1, islandMap.getVerticalDimension());
    }
}
