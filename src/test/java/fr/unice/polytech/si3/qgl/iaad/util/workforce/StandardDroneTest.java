package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class StandardDroneTest
{
    @Test
    public void moveDrone() throws Exception
    {
        Drone drone = new Drone(Compass.E);

        Point expected = new Point();
        assertEquals(expected, drone.getLocation());

        drone.fly();
        expected.translate(1, 0);
        assertEquals(expected, drone.getLocation());

        drone.heading(Compass.S);
        expected.translate(1, 1);
        assertEquals(expected, drone.getLocation());

        drone.heading(Compass.W);
        expected.translate(-1, 1);
        assertEquals(expected, drone.getLocation());

        drone.heading(Compass.N);
        expected.translate(-1, -1);
        assertEquals(expected, drone.getLocation());

        drone.fly();
        expected.translate(0, -1);
        assertEquals(expected, drone.getLocation());
    }
}