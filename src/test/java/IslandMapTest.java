import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/*
 * @author Alexandre Clement
 *         Created the 24/11/2016.
 */

public class IslandMapTest
{
    private IslandMap map;

    @Before
    public void before() { map = new IslandMap(); }

    /**
     * test init conditions
     * @throws InvalidMapException, if bad init conditions
     */
    @Test
    public void IslandMapConstructorTest() throws InvalidMapException
    {
        assertEquals(1, map.getVerticalDimension());
        assertEquals(1, map.getHorizontalDimension());
        assertEquals(new Point(), map.getDroneCoordinates());
        assertTrue(map.pointExist(new Point(0,0)));
        assertFalse(map.isFinished());
    }

    /**
     * number of available points from the drone location
     */
    @Test
    public void getNumberOfAvailablePointsTest()
    {
        for(Direction direction : Direction.values())
        {
            assertEquals(0, map.getNumberOfAvailablePoints(direction));
            map.setGround(direction, 5);
            map.setOutOfRange(direction, 10);
            assertEquals(10, map.getNumberOfAvailablePoints(direction));
        }
    }

    /**
     * grows the islandMap in function of direction and a number of points
     * tests the dimensions of the map
     */
    @Test
    public void setGround_dimensions_Test()
    {
        for(Direction direction : Direction.values())
        {
            map.setGround(direction, 0);
            assertEquals(1, map.getNumberOfAvailablePoints(direction));
            map.setGround(direction, 10);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            map.setGround(direction, 10);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            assertFalse(map.isDirectionFinished(direction));
            map.setOutOfRange(direction, 11);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            map.setGround(direction, 14);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            map.setGround(direction, 4);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            assertTrue(map.isDirectionFinished(direction));
        }
    }

    /**
     * grows the islandMap in function of direction and a number of points
     * tests is ground element is sets
     */
    @Test
    public void setGround_groundBiome_Test() throws InvalidMapException
    {
        map.setGround(Direction.E, 0);
        assertTrue(map.hasElement(new Point(1, 0), Element.GROUND));

        map.setGround(Direction.S, 0);
        assertTrue(map.hasElement(new Point(0, 1), Element.GROUND));

        map.setGround(Direction.W, 0);
        assertTrue(map.hasElement(new Point(), Element.GROUND));

        map.setGround(Direction.N, 0);
        assertTrue(map.hasElement(new Point(1, 0), Element.GROUND));
    }

    /**
     * gets if a direction of the map is finished
     */
    @Test
    public void isDirectionFinishedTest()
    {
        for(Direction direction : Direction.values())
        {
            assertFalse(map.isDirectionFinished(direction));
            map.setOutOfRange(direction, 11);
            assertTrue(map.isDirectionFinished(direction));
        }
    }

    /**
     * gets is all the directions of the map are finished
     */
    @Test
    public void isFinishedTest()
    {
        int count=1;

        for(Direction direction : Direction.values())
        {
            map.setOutOfRange(direction, 2);
            if(count==4) assertTrue(map.isFinished());
            else assertFalse(map.isFinished());
            count++;
        }
    }

    /**
     * locks a direction
     * when a direction is finished, the method does nothing
     */
    @Test
    public void setOutOfRangeTest()
    {
        for(Direction direction : Direction.values())
        {
            assertFalse(map.isDirectionFinished(direction));
            map.setGround(direction, 5);

            map.setOutOfRange(direction, 11);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            assertTrue(map.isDirectionFinished(direction));

            map.setOutOfRange(direction, 4);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            assertTrue(map.isDirectionFinished(direction));

            map.setOutOfRange(direction, 15);
            assertEquals(11, map.getNumberOfAvailablePoints(direction));
            assertTrue(map.isDirectionFinished(direction));
        }
        assertTrue(map.isFinished());
    }

    /**
     * checks the drone coordinates when drone moveLocation
     */
    @Test
    public void moveDroneTest() throws InvalidMapException
    {
        // moves in the map :

        map.setGround(Direction.E, 5);
        map.setGround(Direction.S, 5);

        map.moveLocation(Direction.E);
        assertEquals(new Point(1,0), map.getDroneCoordinates());
        map.moveLocation(Direction.S);
        assertEquals(new Point(1,1), map.getDroneCoordinates());
        map.moveLocation(Direction.W);
        assertEquals(new Point(0, 1), map.getDroneCoordinates());
        map.moveLocation(Direction.N);
        assertEquals(new Point(), map.getDroneCoordinates());

        // tries to moveLocation out the map :

        for(Direction direction : Direction.values())
        {
            InvalidMapException positionException=null; map=new IslandMap();

            try { map.moveLocation(direction); }
            catch(InvalidMapException exception) { positionException=exception; }
            assertNotNull(positionException);
        }
    }

    /**
     * returns true if the element is found
     */
    @Test
    public void hasElementTest() throws InvalidMapException
    {
        map.setGround(Direction.E, 5);

        for(Element element:Element.values())
        {
            assertFalse(map.hasElement(map.getDroneCoordinates(), element));
            map.addBiomes(element);
        }

        for(Element element:Element.values())
        {
            assertTrue(map.hasElement(map.getDroneCoordinates(), element));

            if(element!=Element.GROUND) assertEquals(1, map.getNumberOfBiomes(element));
            else assertEquals(2, map.getNumberOfBiomes(element));
        }

        // checks if old elements are still in the same point after the drone moved
        map.moveLocation(Direction.E);

        for(Element element:Element.values())
        {
            assertTrue(map.hasElement(new Point(), element));

            if(element!=Element.GROUND) assertEquals(1, map.getNumberOfBiomes(element));
            else assertEquals(2, map.getNumberOfBiomes(element));
        }

        // checks if old elements are still in the same point after the map changed
        map.setOutOfRange(Direction.N, 4);

        for(Element element:Element.values())
        {
            assertTrue(map.hasElement(new Point(0, 4), element));

            if(element!=Element.GROUND) assertEquals(1, map.getNumberOfBiomes(element));
            else assertEquals(2, map.getNumberOfBiomes(element));
        }

        // repeats the operation at the new drone coordinates
        for(Element element:Element.values())
        {
            assertFalse(map.hasElement(map.getDroneCoordinates(), element));
            map.addBiomes(element);
        }

        for(Element element:Element.values())
            assertTrue(map.hasElement(map.getDroneCoordinates(), element));
    }

    @Test
    public void emergencySiteTest() throws InvalidMapException
    {
        assertEquals(map.getEmergencySiteId(), "");
        map.addEmergencySite("toto");
        assertEquals(map.getEmergencySiteId(), "toto");
        assertTrue(map.hasElement(new Point(), Element.EMERGENCY_SITE));
    }

    @Test
    public void getNumberOfBiomes() throws InvalidMapException
    {
        map.setGround(Direction.E, 0);
        map.setGround(Direction.N, 0);

        for(Element element:Element.values())
        {
            if(element!=Element.GROUND) assertEquals(0, map.getNumberOfBiomes(element));
            else assertEquals(2, map.getNumberOfBiomes(element));
        }

        map.addBiomes(Element.ALPINE, Element.BEACH);
        map.addEmergencySite("id");
        map.addCreeks("id1", "id2");

        map.moveLocation(Direction.E);
        map.moveLocation(Direction.N);

        assertEquals(1, map.getNumberOfBiomes(Element.ALPINE));
        assertEquals(1, map.getNumberOfBiomes(Element.BEACH));
        assertEquals(1, map.getNumberOfBiomes(Element.EMERGENCY_SITE));
        assertEquals(2, map.getNumberOfBiomes(Element.CREEK));

        map.addBiomes(Element.ALPINE);
        assertEquals(2, map.getNumberOfBiomes(Element.ALPINE));
    }

    @Test
    public void creeksTest() throws InvalidMapException
    {
        assertEquals(map.getCreekIds(new Point()).length, 1);
        map.addCreeks("id-1", "id-2");
        assertEquals(map.getCreekIds(new Point()).length, 2);
        map.addCreeks("id-3", "id-4");
        assertEquals(map.getCreekIds(new Point()).length, 4);

        String ids[]=map.getCreekIds(new Point());

        for(int i=0; i<ids.length; i++) assertEquals(ids[i], "id-"+(i+1));

        assertTrue(map.hasElement(new Point(), Element.CREEK));
        assertEquals(4, map.getNumberOfBiomes(Element.CREEK));
    }

    @Test
    public void getBiomesTest() throws InvalidMapException
    {
        map.addBiomes(Element.ALPINE);
        assertEquals(1, map.getBiomes(new Point()).length);
        assertEquals(Element.ALPINE, map.getBiomes(new Point())[0]);

        map.addBiomes(Element.BEACH, Element.ALPINE, Element.GLACIER, Element.MANGROVE);
        assertEquals(5, map.getBiomes(new Point()).length);

        assertEquals(Element.BEACH, map.getBiomes(new Point())[1]);
        assertEquals(Element.ALPINE, map.getBiomes(new Point())[2]);
        assertEquals(Element.GLACIER, map.getBiomes(new Point())[3]);
        assertEquals(Element.MANGROVE, map.getBiomes(new Point())[4]);

        map.setGround(Direction.E, 5);

        assertEquals(5, map.getBiomes(new Point()).length);
        assertEquals(Element.ALPINE, map.getBiomes(new Point())[0]);
        assertEquals(Element.BEACH, map.getBiomes(new Point())[1]);
        assertEquals(Element.ALPINE, map.getBiomes(new Point())[2]);
        assertEquals(Element.GLACIER, map.getBiomes(new Point())[3]);
        assertEquals(Element.MANGROVE, map.getBiomes(new Point())[4]);
    }

    @Test
    public void deleteBiomeTest() throws InvalidMapException
    {
        for(Element element:Element.values())
        {
            assertEquals(0, map.getBiomes(new Point()).length);
            map.deleteBiome(new Point(), element);
            assertEquals(0, map.getBiomes(new Point()).length);
        }

        map.addBiomes(Element.ALPINE);
        map.deleteBiome(new Point(), Element.ALPINE);
        assertEquals(0, map.getBiomes(new Point()).length);

        map.addBiomes(Element.ALPINE, Element.BEACH, Element.ALPINE, Element.GLACIER, Element.MANGROVE);

        map.deleteBiome(new Point(), Element.ALPINE);
        assertEquals(3, map.getBiomes(new Point()).length);

        assertEquals(Element.BEACH, map.getBiomes(new Point())[0]);
        assertEquals(Element.GLACIER, map.getBiomes(new Point())[1]);
        assertEquals(Element.MANGROVE, map.getBiomes(new Point())[2]);

        map.deleteBiome(new Point(), Element.BEACH);
        assertEquals(2, map.getBiomes(new Point()).length);

        assertEquals(Element.GLACIER, map.getBiomes(new Point())[0]);
        assertEquals(Element.MANGROVE, map.getBiomes(new Point())[1]);

        map.addBiomes(Element.ALPINE);
        assertEquals(3, map.getBiomes(new Point()).length);

        map.deleteBiome(new Point(), Element.ALPINE);
        assertEquals(2, map.getBiomes(new Point()).length);

        assertEquals(Element.GLACIER, map.getBiomes(new Point())[0]);
        assertEquals(Element.MANGROVE, map.getBiomes(new Point())[1]);

        map.addBiomes(Element.ALPINE);
        assertEquals(3, map.getBiomes(new Point()).length);

        map.deleteBiome(new Point(), Element.MANGROVE);
        assertEquals(2, map.getBiomes(new Point()).length);

        assertEquals(Element.GLACIER, map.getBiomes(new Point())[0]);
        assertEquals(Element.ALPINE, map.getBiomes(new Point())[1]);

        map.deleteBiome(new Point(), Element.GLACIER);
        map.deleteBiome(new Point(), Element.ALPINE);
        assertEquals(0, map.getBiomes(new Point()).length);

        map.addBiomes(Element.ALPINE, Element.GLACIER);
        map.deleteBiome(new Point(), Element.GLACIER);
        assertEquals(1, map.getBiomes(new Point()).length);
        assertEquals(Element.ALPINE, map.getBiomes(new Point())[0]);
        map.deleteBiome(new Point(), Element.ALPINE);
        assertEquals(0, map.getBiomes(new Point()).length);

        map.addBiomes(Element.ALPINE, Element.GLACIER);
        map.deleteBiome(new Point(), Element.ALPINE);
        assertEquals(1, map.getBiomes(new Point()).length);
        assertEquals(Element.GLACIER, map.getBiomes(new Point())[0]);
        map.deleteBiome(new Point(), Element.GLACIER);
        assertEquals(0, map.getBiomes(new Point()).length);
    }
}