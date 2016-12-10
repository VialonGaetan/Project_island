import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaad.Direction.N;
import static org.junit.Assert.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Théo on 07/12/2016.
 */
public class CreekTest {

    Creek creek;
    IslandMap map;
    Drone drone;

    @Before
    public void init() throws InvalidMapException { //initialisation et création de la map
        this.map = new IslandMap();
        String idCreek = "JeSuisIdDeCreekLaPlusProche";
        String idSite = "JeSuisIdDeSite";
        this.creek = new Creek(map);
        this.drone = new Drone(10000,Direction.E,map);
        map.setOutOfRange(Direction.E, 4);
        map.setOutOfRange(Direction.S, 4);
        map.moveDrone(Direction.E);
        map.addCreeks("Creek1");
        map.moveDrone(Direction.E);
        map.addCreeks("Creek2");
        map.moveDrone(Direction.S);
        map.addCreeks(idCreek);
        map.moveDrone(Direction.E);
        map.addEmergencySite(idSite);
        //System.out.println(map.getEmergencySiteId());
        //System.out.println(map.getCreekIds(new Point(2,1))[0]);
       // System.out.println(map.getCreekIds(new Point(2,0))[0]);
       // System.out.println(map.getCreekIds(new Point(1,0))[0]);
    }

    @Test
    public void mapWellCreatedTest()
    {
        assertEquals(map.getVerticalDimension(),5);
        assertEquals(map.getHorizontalDimension(),5);
    }
    @Test
    public void getClosestTest() throws InvalidMapException {
        assertEquals(creek.getClosest(map), new Point(2,1));
    }

    @Test
    public void egaliteMethodeTest() throws InvalidMapException {
        //assertEquals(new Point(2,1),creek.getClosest(map));
        //assertEquals(map.getCreekIds(new Point(2,1))[0],map.getCreekIds(creek.getClosest(map))[0]);
        assertEquals(creek.getClosestID(new Point(2,1)).toString(),creek.getClosestID(creek.getClosest(map)).toString());
    }
    @Test
    public void getClosestIDTest() throws InvalidMapException {
        System.out.println(creek.getClosestID(new Point(2,1)));
        System.out.println(creek.getClosestID(creek.getClosest(map)));
        assertEquals(creek.getClosestID(creek.getClosest(map)),"JeSuisIdDeCreekLaPlusProche");
    }

}
