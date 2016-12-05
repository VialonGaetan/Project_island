import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Théo
 */
public class DroneTest {

    int budget;
    Direction direction;
    IslandMap map;
    Drone drone;

    @Test
    public void doActionTest() throws InvalidMapException {
        direction = Direction.E;
        map = new IslandMap();
        drone = new Drone(100,direction,map);
        Stop stop = new Stop();
        assertTrue(drone.doAction().getClass().isInstance(stop));
    }

}
