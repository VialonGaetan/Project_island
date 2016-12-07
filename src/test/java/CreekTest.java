import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Théo on 07/12/2016.
 */
public class CreekTest {

    Creek creek;
    IslandMap map;

    @Before
    public void init() throws InvalidMapException {
        this.map = new IslandMap();
        this.creek = new Creek(map);
    }

    @Test
    public void getClosestTest() throws InvalidMapException {
        for (int i=0; i<100; i++){
            for (int j=0; j<10; j++) {
                creek.creeks.add(new Point(i,j));
            }
        }
        creek.emSite=new Point(0,0);
        Point p = creek.getClosest(map);
        assertEquals(p, new Point(0,0));
    }

}
