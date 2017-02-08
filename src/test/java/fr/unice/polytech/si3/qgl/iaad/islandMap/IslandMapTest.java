package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 24/11/2016.
 */
public class IslandMapTest
{
    private IslandMap map;

    @Before
    public void before() { map = new IslandMap(); }

    @Test
    public void IslandMapConstructor() throws InvalidMapException
    {
        assertEquals(1, map.getVerticalDimension());
        assertEquals(1, map.getHorizontalDimension());
        assertEquals(new Point(), map.getLocation());
        assertTrue(map.pointExist(new Point(0,0)));
        assertFalse(map.isFinished());
    }

    /**
     * number of points available between the current location and the limit of the map
     * depends of the direction
     */
    @Test
    public void getNumberOfAvailablePoints()
    {
        for(Direction direction : Direction.values())
        {
            assertEquals(0, map.getNumberOfAvailablePoints(direction));
            map.setGround(direction, 5);
            map.setOutOfRange(direction, 10);
            assertEquals(10, map.getNumberOfAvailablePoints(direction));
        }
    }

    @Test
    public void setGroundDimensions()
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

    @Test
    public void moveLocationTest() throws InvalidMapException
    {
        // moves in the islandMap :

        map.setGround(Direction.E, 5);
        map.setGround(Direction.S, 5);

        map.moveLocation(Direction.E);
        assertEquals(new Point(1,0), map.getLocation());
        map.moveLocation(Direction.S);
        assertEquals(new Point(1,1), map.getLocation());
        map.moveLocation(Direction.W);
        assertEquals(new Point(0, 1), map.getLocation());
        map.moveLocation(Direction.N);
        assertEquals(new Point(), map.getLocation());

        // tries to moveLocation out the islandMap :

        for(Direction direction : Direction.values())
        {
            InvalidMapException positionException=null; map=new IslandMap();

            try { map.moveLocation(direction); }
            catch(InvalidMapException exception) { positionException=exception; }
            assertNotNull(positionException);
        }
    }

    /**
     * check if the emergency site is unique
     * check if the emergency site is correctly added
     * @throws InvalidMapException if bad coordinates
     */
    @Test
    public void emergencySiteTest() throws InvalidMapException
    {
        assertEquals(map.getEmergencySiteId(), "");
        map.addEmergencySite("toto");
        assertEquals(map.getEmergencySiteId(), "toto");
        map.addEmergencySite("titi");
        assertEquals(map.getEmergencySiteId(), "toto");

        assertEquals(1, map.getElements(new Point()).length);
        assertEquals(Element.EMERGENCY_SITE, map.getElements(new Point())[0]);
    }

    /**
     * check map dimensions after zoom
     * @throws InvalidMapException if bad coordinates
     */
    @Test
    public void zoomDimensionsTest() throws InvalidMapException
    {
        map.addBiomes(Biome.MANGROVE);
        map.zoom();
        assertEquals(3, map.getVerticalDimension());
        assertEquals(3, map.getHorizontalDimension());

        map.zoom();
        assertEquals(3, map.getVerticalDimension());
        assertEquals(3, map.getHorizontalDimension());
    }

    /**
     * check data in the map after zoom
     * @throws InvalidMapException if bad coordinates
     */
    @Test
    public void zoomBiomesTest() throws InvalidMapException
    {
        map.addBiomes(Biome.MANGROVE);
        map.zoom();

        for(int x=0; x<map.getHorizontalDimension(); x++)
        {
            for(int y=0; y<map.getVerticalDimension(); y++)
            {
                assertEquals(1, map.getBiomes(new Point()).length);
                assertTrue(map.hasBiome(new Point(x, y), Biome.MANGROVE));
            }
        }

        map.zoom();

        for(int x=0; x<map.getHorizontalDimension(); x++)
        {
            for(int y=0; y<map.getVerticalDimension(); y++)
            {
                assertEquals(1, map.getBiomes(new Point()).length);
                assertTrue(map.hasBiome(new Point(x, y), Biome.MANGROVE));
            }
        }
    }

    /**
     * check location just after zoom
     * @throws InvalidMapException if bad coordinates
     */
    @Test
    public void landLocationTest() throws InvalidMapException
    {
        map.addBiomes(Biome.MANGROVE);
        map.zoom();
        assertEquals(1, (int)(map.getLocation().getX()));
        assertEquals(1, (int)(map.getLocation().getY()));
    }

    @Test
    public void getBiomesTest() throws InvalidMapException
    {
        map.addBiomes(Biome.ALPINE, Biome.BEACH);
        map.setOutOfRange(Direction.E, 10);
        map.setOutOfRange(Direction.S, 10);

        assertEquals(2, map.getBiomes(new Point()).length);
        assertEquals(Biome.ALPINE, map.getBiomes(new Point())[0]);
        assertEquals(Biome.BEACH, map.getBiomes(new Point())[1]);

        map.moveLocation(Direction.E);
        map.moveLocation(Direction.S);

        map.addBiomes(Biome.GLACIER);
        assertEquals(1, map.getBiomes(map.getLocation()).length);
        assertEquals(Biome.GLACIER, map.getBiomes(map.getLocation())[0]);
    }

    /**
     * test hasBiome
     * test hasElement
     * @throws InvalidMapException if bad coordinates
     */
    @Test
    public void hasTest() throws InvalidMapException
    {
        assertFalse(map.hasBiome(new Point(), Biome.ALPINE));
        assertFalse(map.hasElement(new Point(), Element.CREEK));

        map.addBiomes(Biome.BEACH);

        assertTrue(map.hasBiome(new Point(), Biome.BEACH));
        assertFalse(map.hasElement(new Point(), Element.CREEK));

        map.addCreeks("id1");

        assertTrue(map.hasBiome(new Point(), Biome.BEACH));
        assertTrue(map.hasElement(new Point(), Element.CREEK));
    }

    @Test
    public void creeksTest() throws InvalidMapException
    {
        assertFalse(map.hasElement(new Point(), Element.CREEK));
        map.addCreeks("id1", "id2");
        assertTrue(map.hasElement(new Point(), Element.CREEK));
        assertEquals(2, map.getCreekIds(new Point()).length);
        assertEquals("id1", map.getCreekIds(new Point())[0]);
        assertEquals("id2", map.getCreekIds(new Point())[1]);
    }

    @Test
    public void deleteBiomeTest() throws InvalidMapException
    {
        map.addBiomes(Biome.BEACH, Biome.GLACIER);
        map.deleteBiomes(new Point(), Biome.ALPINE);
        assertEquals(2, map.getBiomes(new Point()).length);
        map.deleteBiomes(new Point(), Biome.BEACH);
        assertEquals(1, map.getBiomes(new Point()).length);
        assertEquals(Biome.GLACIER, map.getBiomes(new Point())[0]);

        map.deleteBiomes(new Point(), Biome.GLACIER);
        assertEquals(0, map.getBiomes(new Point()).length);

        map.deleteBiomes(new Point(), Biome.GLACIER);
        assertEquals(0, map.getBiomes(new Point()).length);
    }

    /**
     * check previous locations
     * map in ground mode
     * @throws InvalidMapException id bad coordinates
     */
    @Test
    public void isAnOldGroundLocationTest() throws InvalidMapException
    {
        assertFalse(map.isAnOldGroundLocation(new Point()));
        map.zoom();
        assertFalse(map.isAnOldGroundLocation(new Point()));
        assertTrue(map.isAnOldGroundLocation(new Point(1, 1)));
        map.moveLocation(Direction.S);
        assertTrue(map.isAnOldGroundLocation(new Point(1, 2)));
    }

    /**
     * check previous locations
     * map in aerial mode
     * @throws InvalidMapException if bad coordinates
     */
    @Test
    public void isAnOldAerialLocationTest() throws InvalidMapException
    {
        assertTrue(map.isAnOldAerialLocation(new Point()));
        map.zoom();
        assertTrue(map.isAnOldAerialLocation(new Point()));
        assertTrue(map.isAnOldAerialLocation(new Point(1, 1)));
        map.moveLocation(Direction.S);
        map.setOutOfRange(Direction.S, 2);
        map.moveLocation(Direction.S);
        assertFalse(map.isAnOldAerialLocation(new Point(1, 3)));
    }
}