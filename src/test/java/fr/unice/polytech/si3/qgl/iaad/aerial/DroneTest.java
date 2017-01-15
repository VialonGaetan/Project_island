package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class DroneTest
{
    private Drone drone;
    private int budget;
    private int lowBudget;
    private IslandMap map;
    private Direction heading;
    private Drone droneWithoutBudget;


    @Before
    public void setUp() throws Exception
    {
        budget = Drone.LOW_BUDGET + 1;
        lowBudget = Drone.LOW_BUDGET - 1;
        heading = Direction.E;
        map = new IslandMap();
        drone = new Drone(budget, heading, map);
        droneWithoutBudget = new Drone(lowBudget, heading, map);
    }

    @Test
    public void doAction() throws Exception
    {
        Action action = drone.doAction();
        assertNotNull(action);
        assertTrue(droneWithoutBudget.doAction() instanceof Stop);
    }

    @Test
    public void getResult() throws Exception
    {

    }

}