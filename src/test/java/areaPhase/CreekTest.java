package areaPhase;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creeks;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

/**
 * @author Théo
 * Created on 07/12/2016.
 */
public class CreekTest {

    private Creeks creek;
    private IslandMap mymap;

    @Before
    public void init() { mymap = new IslandMap(); }

    @Test
    public void oneCreek() throws InvalidMapException
    {
        mymap.addCreeks("id1");
        mymap.setOutOfRange(Direction.E, 2);
        mymap.moveLocation(Direction.E);
        mymap.addEmergencySite("emgcy");

        creek = new Creeks(mymap);

        assertEquals(creek.getClosestCreekId(), "id1");
    }

    @Test
    public void twoCreeksOnSameSquare() throws InvalidMapException
    {
        mymap.addCreeks("id1", "id2");
        mymap.setOutOfRange(Direction.E, 2);
        mymap.moveLocation(Direction.E);
        mymap.addEmergencySite("emgcy");

        creek = new Creeks(mymap);

        assertEquals(creek.getClosestCreekId(), "id1");
    }

    @Test
    public void creekAndEmergencySiteOnSameSquare() throws InvalidMapException
    {
        mymap.setOutOfRange(Direction.E, 50);
        mymap.setOutOfRange(Direction.S, 50);
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.S);
        mymap.addCreeks("creek21");
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.S);
        mymap.addCreeks("creek42");
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.S);
        mymap.addCreeks("creek63");
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.S);
        mymap.addCreeks("creek84");
        mymap.addEmergencySite("site");
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.E);
        mymap.moveLocation(Direction.S);
        mymap.addCreeks("creek105");

        creek = new Creeks(mymap);
        assertEquals(new Point(8, 4), creek.getClosestCreekLocation());
        assertEquals("creek84", creek.getClosestCreekId());
    }

    @Test
    public void severalCreeks() throws InvalidMapException
    {
        mymap.addEmergencySite("00");
        mymap.setOutOfRange(Direction.E, 10);
        mymap.setOutOfRange(Direction.S, 10);
        mymap.moveLocation(Direction.S);
        mymap.addCreeks("01");
        mymap.moveLocation(Direction.N);
        mymap.moveLocation(Direction.E);
        mymap.addCreeks("10");

        creek = new Creeks(mymap);
        assertEquals("01", creek.getClosestCreekId());
    }

    @Test(expected=InvalidMapException.class)
    public void noCreekTest() throws InvalidMapException
    {
        mymap.addEmergencySite("site");
        Creeks creek = new Creeks(mymap);
        creek.getClosestCreekId();
    }

    @Test(expected=InvalidMapException.class)
    public void noEmergencySiteTest() throws InvalidMapException
    {
        mymap.addCreeks("creek");
        Creeks creek = new Creeks(mymap);
        creek.getClosestCreekId();
    }

    @Test(expected=InvalidMapException.class)
    public void nothingTest() throws InvalidMapException
    {
        Creeks creek = new Creeks(mymap);
        creek.getClosestCreekId();
    }
}
