package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class StandardDroneTest
{
    @Test
    public void moveDrone() throws Exception
    {
        Drone drone = new Drone(Direction.E);

        Point expected = new Point();
        assertEquals(expected, drone.getLocation());

        drone.fly();
        expected.translate(1, 0);
        assertEquals(expected, drone.getLocation());

        drone.heading(Direction.S);
        expected.translate(1, 1);
        assertEquals(expected, drone.getLocation());

        drone.heading(Direction.W);
        expected.translate(-1, 1);
        assertEquals(expected, drone.getLocation());

        drone.heading(Direction.N);
        expected.translate(-1, -1);
        assertEquals(expected, drone.getLocation());

        drone.fly();
        expected.translate(0, -1);
        assertEquals(expected, drone.getLocation());
    }
}