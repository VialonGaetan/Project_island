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
    IslandMap mymap;

    @Before
    public void init() throws InvalidMapException { //initialisation et création de la map
        this.mymap = new IslandMap();
        this.creek= new Creek(mymap);
        mymap.setOutOfRange(Direction.E, 50);
        mymap.setOutOfRange(Direction.S, 50);
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.S);
        mymap.addCreeks("creek21");
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.S);
        mymap.addCreeks("creek42");
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.S);
        mymap.addCreeks("creek63");
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.S);
        mymap.addCreeks("creek84");
        mymap.addEmergencySite("site");
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.E);
        mymap.moveDrone(Direction.S);
        mymap.addCreeks("creek105");
    }

    @Test
    public void aTest() throws InvalidMapException {
        assertEquals(new Point(8, 4), creek.getClosest(mymap));
        assertEquals("creek84", creek.getClosestID(creek.getClosest(mymap)));
    }

}
