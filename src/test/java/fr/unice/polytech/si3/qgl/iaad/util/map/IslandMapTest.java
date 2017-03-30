package fr.unice.polytech.si3.qgl.iaad.util.map;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 24/11/2016.
 */
public class IslandMapTest
{
    private IslandMap islandMap;

    /**
     * init an empty map
     */
    @Before
    public void setUp() { islandMap = new IslandMap(); }

    /**
     * test if the map is like a unique point
     * test if the point (0, 0) is on the map
     * test if the tile of the point is empty
     */
    @Test
    public void constructor()
    {
        assertEquals(0, islandMap.getTile(new Point()).getBiomes().size());
        assertTrue(islandMap.isOnMap(new Point()));

        for(Compass compass : Compass.values())
        {
            assertEquals(0, islandMap.getDimension(compass));
        }
    }

    /**
     * test if the map grows without changing the current tiles
     * test if dimensions are correctly changed
     */
    @Test
    public void grow()
    {
        islandMap.grow(Compass.E, 1);
        assertEquals(1, islandMap.getDimension(Compass.E));
        assertTrue(islandMap.isOnMap(new Point()));
        assertTrue(islandMap.isOnMap(new Point(1, 0)));

        islandMap.grow(Compass.S, 1);
        assertEquals(1, islandMap.getDimension(Compass.S));
        assertTrue(islandMap.isOnMap(new Point()));
        assertTrue(islandMap.isOnMap(new Point(1, 0)));
        assertTrue(islandMap.isOnMap(new Point(1, 1)));

        islandMap.grow(Compass.W, 1);
        assertEquals(1, islandMap.getDimension(Compass.W));
        assertTrue(islandMap.isOnMap(new Point()));
        assertTrue(islandMap.isOnMap(new Point(1, 0)));
        assertTrue(islandMap.isOnMap(new Point(1, 1)));
        assertTrue(islandMap.isOnMap(new Point(0, 1)));

        islandMap.grow(Compass.N, 1);
        assertEquals(1, islandMap.getDimension(Compass.N));
        assertTrue(islandMap.isOnMap(new Point()));
        assertTrue(islandMap.isOnMap(new Point(1, 0)));
        assertTrue(islandMap.isOnMap(new Point(1, -1)));
        assertTrue(islandMap.isOnMap(new Point(0, -1)));
    }

    /**
     * test if a point is on the map
     */
    @Test
    public void isOnMap()
    {
        assertTrue(islandMap.isOnMap(new Point()));
        assertFalse(islandMap.isOnMap(new Point(0, 1)));
        islandMap.grow(Compass.E, 2);
        assertEquals(2, islandMap.getDimension(Compass.E));
        assertTrue(islandMap.isOnMap(new Point(1, 0)));
        assertTrue(islandMap.isOnMap(new Point(2, 0)));
        islandMap.grow(Compass.S, 10);
        assertEquals(10, islandMap.getDimension(Compass.S));
        assertTrue(islandMap.isOnMap(new Point(1, 2)));
        assertFalse(islandMap.isOnMap(new Point(0, -3)));
        assertFalse(islandMap.isOnMap(new Point(2, 11)));
    }

    /**
     * test if we get the correct tile from the map
     * test if we have an empty tile if the point has never been visited in the map
     */
    @Test
    public void getTile()
    {
        List<Biome> biomes = new ArrayList<>();
        biomes.add(Biome.ALPINE);

        assertEquals(0, islandMap.getTile(new Point()).getBiomes().size());
        Tile tile = new Tile();
        tile.addBiomes(biomes);
        islandMap.getTile(new Point()).addBiomes(biomes);
        assertEquals(1, islandMap.getTile(new Point()).getBiomes().size());
        assertEquals(Biome.ALPINE, islandMap.getTile(new Point()).getBiomes().get(0));
        islandMap.grow(Compass.E, 1);
        assertEquals(0, islandMap.getTile(new Point(1, 0)).getBiomes().size());
        assertNull(islandMap.getTile(new Point(2, 0)));
    }

    /**
     * test if the map contains new or old points
     */
    @Test
    public void isOnMapAfterZoom()
    {
        islandMap.zoom();

        for(Compass compass : Compass.values())
        {
            assertEquals(0, islandMap.getDimension(compass));
        }

        assertTrue(islandMap.isOnMap(new Point()));
    }

    /**
     * test if the tiles are still on the map
     */
    @Test
    public void getTileAfterZoom()
    {
        islandMap.zoom();

        for(Compass compass : Compass.values())
        {
            assertEquals(0, islandMap.getDimension(compass));
        }

        assertTrue(islandMap.getTile(new Point()).equals(new Tile()));
    }

    /**
     * test dimensions of the map and if points are still on the map
     */
    @Test
    public void zoomAndDimensions()
    {
        IslandMap islandMap = new IslandMap();
        assertEquals(islandMap.getDimension(Compass.N),0);
        assertEquals(islandMap.getDimension(Compass.S),0);
        assertEquals(islandMap.getDimension(Compass.E),0);
        assertEquals(islandMap.getDimension(Compass.W),0);
        assertTrue(islandMap.isOnMap(new Point(0,0)));
        assertFalse(islandMap.isOnMap(new Point(1,0)));
        assertFalse(islandMap.isOnMap(new Point(1,1)));
        islandMap.grow(Compass.S,3);
        assertEquals(islandMap.getDimension(Compass.N),0);
        assertEquals(islandMap.getDimension(Compass.S),3);
        assertEquals(islandMap.getDimension(Compass.E),0);
        assertEquals(islandMap.getDimension(Compass.W),0);
        assertTrue(islandMap.isOnMap(new Point(0,0)));
        assertTrue(islandMap.isOnMap(new Point(0,1)));
        assertTrue(islandMap.isOnMap(new Point(0,2)));
        assertTrue(islandMap.isOnMap(new Point(0,3)));
        assertFalse(islandMap.isOnMap(new Point(1,1)));
        islandMap.zoom();
        assertEquals(islandMap.getDimension(Compass.N),0);
        assertEquals(islandMap.getDimension(Compass.S),9);
        assertEquals(islandMap.getDimension(Compass.E),0);
        assertEquals(islandMap.getDimension(Compass.W),0);
        assertTrue(islandMap.isOnMap(new Point(0,0)));
        assertTrue(islandMap.isOnMap(new Point(0,1)));
        assertTrue(islandMap.isOnMap(new Point(0,2)));
        assertTrue(islandMap.isOnMap(new Point(0,3)));
        assertFalse(islandMap.isOnMap(new Point(2,1)));
    }
}