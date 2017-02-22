package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Théo on 02/02/2017.
 */
public class TracerTest {

    IslandMap map;
    Tracer tracer;

    @Before
    public void init() throws InvalidMapException {
        this.map = new IslandMap();
        this.tracer=new Tracer(map);
        map.setGround(Direction.E,100);
        map.setGround(Direction.S,100);
        tracer.isAnOldPosition(map,new Point(4,4));
    }

    @Test
    public void isAnOldPositionTest() throws InvalidMapException {
        assertFalse(tracer.isAnOldPosition(map,new Point(0,0)));
        assertTrue(tracer.isAnOldPosition(map,new Point(0,0)));
        assertFalse(tracer.isAnOldPosition(map,new Point(1,1)));
        assertTrue(tracer.isAnOldPosition(map,new Point(1,1)));
        assertFalse(tracer.isAnOldPosition(map,new Point(2,2)));
        assertTrue(tracer.isAnOldPosition(map,new Point(2,2)));
        assertFalse(tracer.isAnOldPosition(map,new Point(3,3)));
        assertTrue(tracer.isAnOldPosition(map,new Point(3,3)));
        assertTrue(tracer.isAnOldPosition(map,new Point(4,4)));
    }


    @Test
    public void isTopAnOldPositionTest() throws InvalidMapException {
        assertFalse(tracer.isTopAnOldPosition(map, new Point(5,6)));
        assertFalse(tracer.isTopAnOldPosition(map, new Point(5,7)));
        assertTrue(tracer.isTopAnOldPosition(map, new Point(4,5)));
        assertEquals(null, tracer.isTopAnOldPosition(map, new Point(0,0)));
    }

    @Test
    public void isBottomAnOldPositionTest() throws InvalidMapException {
        assertFalse(tracer.isTopAnOldPosition(map, new Point(5,6)));
        assertFalse(tracer.isTopAnOldPosition(map, new Point(6,7)));
        assertTrue(tracer.isBottomAnOldPosition(map, new Point(4,3)));
        assertEquals(null, tracer.isBottomAnOldPosition(map, new Point(0,map.getVerticalDimension())));
    }

    @Test
    public void isLeftAnOldPositionTest() throws InvalidMapException {
        assertFalse(tracer.isLeftAnOldPosition(map, new Point(10,10)));
        assertFalse(tracer.isLeftAnOldPosition(map, new Point(55,55)));
        assertTrue(tracer.isLeftAnOldPosition(map, new Point(5,4)));
        assertEquals(null, tracer.isLeftAnOldPosition(map, new Point(0,0)));
    }

    @Test
    public void isRightAnOldPositionTest() throws InvalidMapException {
        assertFalse(tracer.isRightAnOldPosition(map, new Point(10,10)));
        assertFalse(tracer.isRightAnOldPosition(map, new Point(55,55)));
        assertTrue(tracer.isRightAnOldPosition(map, new Point(3,4)));
        assertEquals(null, tracer.isRightAnOldPosition(map, new Point(map.getHorizontalDimension(),0)));
    }

}
