package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.EmergencySite;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/02/2017.
 */
public class EuclideanTest
{
    private Board board;
    private ArrayList<EmergencySite> emergencySite;
    private ArrayList<Creek> creeks;

    @Test
    public void getClosestCreek() throws Exception
    {
        board = new StandardBoard();
        board.grow(Direction.E, 10);
        board.grow(Direction.S, 20);
        board.grow(Direction.N, 5);
        board.grow(Direction.W, 50);
        assertFalse(Euclidean.getClosestCreekToTheEmergencySite(board).isPresent());

        emergencySite = new ArrayList<>();
        emergencySite.add(new EmergencySite());
        board.getTile(new Point()).addEmergencySites(emergencySite);
        assertFalse(Euclidean.getClosestCreekToTheEmergencySite(board).isPresent());

        creeks = new ArrayList<>();
        creeks.add(new Creek("-45, -3"));
        board.getTile(new Point(-45, -3)).addCreeks(creeks);
        assertTrue(Euclidean.getClosestCreekToTheEmergencySite(board).isPresent());
        assertEquals(new Creek("-45, -3"), Euclidean.getClosestCreekToTheEmergencySite(board).get());

        creeks.clear();
        creeks.add(new Creek("5, 10"));
        board.getTile(new Point(5, 10)).addCreeks(creeks);
        assertEquals(new Creek("5, 10"), Euclidean.getClosestCreekToTheEmergencySite(board).get());

        creeks.clear();
        creeks.add(new Creek("-35, 5"));
        board.getTile(new Point(-35, 5)).addCreeks(creeks);
        assertEquals(new Creek("5, 10"), Euclidean.getClosestCreekToTheEmergencySite(board).get());

        creeks.clear();
        creeks.add(new Creek("1, -2"));
        board.getTile(new Point(1, -2)).addCreeks(creeks);
        assertEquals(new Creek("1, -2"), Euclidean.getClosestCreekToTheEmergencySite(board).get());
    }
}