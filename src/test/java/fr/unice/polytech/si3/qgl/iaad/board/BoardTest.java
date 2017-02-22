package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 24/11/2016.
 */
public class BoardTest
{
    private Board board;

    @Before
    public void before() { board = new Board(); }

    @Test
    public void IslandMapConstructor() throws InvalidMapException
    {
        assertEquals(0, board.getTile(new Point()).getBiomes().size());
        assertTrue(board.isOnBoard(new Point()));
    }

    @Test
    public void grow()
    {
        board.grow(Direction.E, 1);
        assertTrue(board.isOnBoard(new Point()));
        assertTrue(board.isOnBoard(new Point(1, 0)));

        board.grow(Direction.S, 1);
        assertTrue(board.isOnBoard(new Point()));
        assertTrue(board.isOnBoard(new Point(1, 0)));
        assertTrue(board.isOnBoard(new Point(1, -1)));

        board.grow(Direction.W, 1);
        assertTrue(board.isOnBoard(new Point()));
        assertTrue(board.isOnBoard(new Point(1, 0)));
        assertTrue(board.isOnBoard(new Point(1, -1)));
        assertTrue(board.isOnBoard(new Point(0, -1)));

        board.grow(Direction.N, 1);
        assertTrue(board.isOnBoard(new Point()));
        assertTrue(board.isOnBoard(new Point(1, 0)));
        assertTrue(board.isOnBoard(new Point(1, -1)));
        assertTrue(board.isOnBoard(new Point(0, -1)));
    }

    @Test
    public void isOnBoard()
    {
        assertTrue(board.isOnBoard(new Point()));
        assertFalse(board.isOnBoard(new Point(0, 1)));
        board.grow(Direction.E, 2);
        assertTrue(board.isOnBoard(new Point(1, 0)));
        assertTrue(board.isOnBoard(new Point(2, 0)));
        board.grow(Direction.S, 10);
        assertTrue(board.isOnBoard(new Point(1, -2)));
        assertFalse(board.isOnBoard(new Point(0, 3)));
        assertFalse(board.isOnBoard(new Point(2, -11)));
    }

    @Test
    public void getTile()
    {
        List<Biome> biomes = new ArrayList<>();
        biomes.add(Biome.ALPINE);

        assertEquals(0, board.getTile(new Point()).getBiomes().size());
        Tile tile = new Tile();
        tile.addBiomes(biomes);
        board.getTile(new Point()).addBiomes(biomes);
        assertEquals(1, board.getTile(new Point()).getBiomes().size());
        assertEquals(Biome.ALPINE, board.getTile(new Point()).getBiomes().get(0));
        board.grow(Direction.E, 1);
        assertEquals(0, board.getTile(new Point(1, 0)).getBiomes().size());
        assertNull(board.getTile(new Point(2, 0)));
    }

    @Test
    public void isOnBoardAfterZoom()
    {
        board.zoom();
        assertTrue(board.isOnBoard(new Point(-1, 1)));
        assertTrue(board.isOnBoard(new Point(1, 1)));
        assertTrue(board.isOnBoard(new Point(0, 1)));
        assertTrue(board.isOnBoard(new Point(-1, 0)));
        assertTrue(board.isOnBoard(new Point()));
        assertTrue(board.isOnBoard(new Point(1, 0)));
        assertTrue(board.isOnBoard(new Point(-1, -1)));
        assertTrue(board.isOnBoard(new Point(0, -1)));
        assertTrue(board.isOnBoard(new Point(1, -1)));
    }

    @Test
    public void getTileAfterZoom()
    {
        board.zoom();
        assertTrue(board.getTile(new Point(-1, 1)).equals(new Tile()));
        assertTrue(board.getTile(new Point(1, 1)).equals(new Tile()));
        assertTrue(board.getTile(new Point(0, 1)).equals(new Tile()));
        assertTrue(board.getTile(new Point(-1, 0)).equals(new Tile()));
        assertTrue(board.getTile(new Point()).equals(new Tile()));
        assertTrue(board.getTile(new Point(1, 0)).equals(new Tile()));
        assertTrue(board.getTile(new Point(-1, -1)).equals(new Tile()));
        assertTrue(board.getTile(new Point(0, -1)).equals(new Tile()));
        assertTrue(board.getTile(new Point(1, -1)).equals(new Tile()));
    }
}