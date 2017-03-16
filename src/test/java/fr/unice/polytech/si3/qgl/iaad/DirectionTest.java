package fr.unice.polytech.si3.qgl.iaad;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Alexandre Clement
 *         Created the 24/11/2016.
 */
public class DirectionTest
{

    @Test
    public void getRightTest()
    {
        Compass direction = Compass.E;
        assertEquals(Compass.S, direction.get(Direction.RIGHT));
        assertNotEquals(Compass.E, direction.get(Direction.RIGHT));
        assertNotEquals(Compass.W, direction.get(Direction.RIGHT));
        assertNotEquals(Compass.N, direction.get(Direction.RIGHT));
        assertNotEquals(direction.get(Direction.BACK), direction.get(Direction.RIGHT));
        assertNotEquals(direction.get(Direction.LEFT), direction.get(Direction.RIGHT));
        assertNotEquals(direction.getVector(), direction.get(Direction.RIGHT));
    }

    @Test
    public void getLeftTest()
    {
        Compass direction = Compass.E;
        assertEquals(Compass.N, direction.get(Direction.LEFT));
        assertNotEquals(Compass.E, direction.get(Direction.LEFT));
        assertNotEquals(Compass.W, direction.get(Direction.LEFT));
        assertNotEquals(Compass.S, direction.get(Direction.LEFT));
        assertNotEquals(direction.get(Direction.BACK), direction.get(Direction.LEFT));
        assertNotEquals(direction.get(Direction.RIGHT), direction.get(Direction.LEFT));
        assertNotEquals(direction.getVector(), direction.get(Direction.LEFT));
    }

    @Test
    public void getBackTest()
    {
        Compass direction = Compass.E;
        assertEquals(Compass.W, direction.get(Direction.BACK));
        assertNotEquals(Compass.E, direction.get(Direction.BACK));
        assertNotEquals(Compass.S, direction.get(Direction.BACK));
        assertNotEquals(Compass.N, direction.get(Direction.BACK));
        assertNotEquals(direction.get(Direction.RIGHT), direction.get(Direction.BACK));
        assertNotEquals(direction.get(Direction.LEFT), direction.get(Direction.BACK));
        assertNotEquals(direction.getVector(), direction.get(Direction.BACK));
    }

    @Test
    public void getVecteurTest()
    {
        Compass direction = Compass.E;
        assertEquals(new Point(1,0), direction.getVector());
        assertNotEquals(new Point(1,1), direction.getVector());
        assertNotEquals(new Point(0,0), direction.getVector());
        assertNotEquals(new Point(0,1), direction.getVector());
        direction = Compass.W;
        assertEquals(new Point(-1,0), direction.getVector());
        assertNotEquals(new Point(1,1), direction.getVector());
        assertNotEquals(new Point(0,0), direction.getVector());
        assertNotEquals(new Point(0,1), direction.getVector());
        direction = Compass.S;
        assertEquals(new Point(0,1), direction.getVector());
        assertNotEquals(new Point(1,1), direction.getVector());
        assertNotEquals(new Point(0,0), direction.getVector());
        assertNotEquals(new Point(1,0), direction.getVector());
        direction = Compass.N;
        assertEquals(new Point(0,-1), direction.getVector());
        assertNotEquals(new Point(1,1), direction.getVector());
        assertNotEquals(new Point(0,0), direction.getVector());
        assertNotEquals(new Point(0,1), direction.getVector());
    }
}