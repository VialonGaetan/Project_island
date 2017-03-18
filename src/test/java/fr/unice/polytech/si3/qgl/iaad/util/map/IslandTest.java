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
public class IslandTest
{
    private IslandMap islandMap;

    @Before
    public void before() { islandMap = new IslandMap(); }

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

    @Test
    public void isOnBoardAfterZoom()
    {
        islandMap.zoom();

        for(Compass compass : Compass.values())
        {
            assertEquals(0, islandMap.getDimension(compass));
        }

        assertTrue(islandMap.isOnMap(new Point()));
    }

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

    @Test
    public void idktest(){
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