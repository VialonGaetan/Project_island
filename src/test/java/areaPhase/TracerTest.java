package areaPhase;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Tracer;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.DynAnyPackage.Invalid;

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
}
