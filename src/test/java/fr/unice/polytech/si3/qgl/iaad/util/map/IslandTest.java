package fr.unice.polytech.si3.qgl.iaad.util.map;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import org.junit.Before;
import org.junit.Ignore;
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
    public void IslandMapConstructor()
    {
        assertEquals(0, islandMap.getTile(new Point()).getBiomes().size());
        assertTrue(islandMap.isOnBoard(new Point()));
    }

    @Test
    public void grow()
    {
        islandMap.grow(Compass.E, 1);
        assertTrue(islandMap.isOnBoard(new Point()));
        assertTrue(islandMap.isOnBoard(new Point(1, 0)));

        islandMap.grow(Compass.S, 1);
        assertTrue(islandMap.isOnBoard(new Point()));
        assertTrue(islandMap.isOnBoard(new Point(1, 0)));
        assertTrue(islandMap.isOnBoard(new Point(1, 1)));

        islandMap.grow(Compass.W, 1);
        assertTrue(islandMap.isOnBoard(new Point()));
        assertTrue(islandMap.isOnBoard(new Point(1, 0)));
        assertTrue(islandMap.isOnBoard(new Point(1, 1)));
        assertTrue(islandMap.isOnBoard(new Point(0, 1)));

        islandMap.grow(Compass.N, 1);
        assertTrue(islandMap.isOnBoard(new Point()));
        assertTrue(islandMap.isOnBoard(new Point(1, 0)));
        assertTrue(islandMap.isOnBoard(new Point(1, -1)));
        assertTrue(islandMap.isOnBoard(new Point(0, -1)));
    }

    @Test
    public void isOnBoard()
    {
        assertTrue(islandMap.isOnBoard(new Point()));
        assertFalse(islandMap.isOnBoard(new Point(0, 1)));
        islandMap.grow(Compass.E, 2);
        assertTrue(islandMap.isOnBoard(new Point(1, 0)));
        assertTrue(islandMap.isOnBoard(new Point(2, 0)));
        islandMap.grow(Compass.S, 10);
        assertTrue(islandMap.isOnBoard(new Point(1, 2)));
        assertFalse(islandMap.isOnBoard(new Point(0, -3)));
        assertFalse(islandMap.isOnBoard(new Point(2, 11)));
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
        assertTrue(islandMap.isOnBoard(new Point(-1, 1)));
        assertTrue(islandMap.isOnBoard(new Point(1, 1)));
        assertTrue(islandMap.isOnBoard(new Point(0, 1)));
        assertTrue(islandMap.isOnBoard(new Point(-1, 0)));
        assertTrue(islandMap.isOnBoard(new Point()));
        assertTrue(islandMap.isOnBoard(new Point(1, 0)));
        assertTrue(islandMap.isOnBoard(new Point(-1, -1)));
        assertTrue(islandMap.isOnBoard(new Point(0, -1)));
        assertTrue(islandMap.isOnBoard(new Point(1, -1)));
    }

    @Test
    public void getTileAfterZoom()
    {
        islandMap.zoom();
        assertTrue(islandMap.getTile(new Point(-1, 1)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(1, 1)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(0, 1)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(-1, 0)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point()).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(1, 0)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(-1, -1)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(0, -1)).equals(new Tile()));
        assertTrue(islandMap.getTile(new Point(1, -1)).equals(new Tile()));
    }
}