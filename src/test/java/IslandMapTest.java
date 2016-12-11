import java.awt.*;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import org.junit.Before;
import org.junit.Test;

/*
 * @author Alexandre Clement
 *         Created the 24/11/2016.
 */

public class IslandMapTest
{
    private IslandMap map;

    @Before
    public void before() { map = new IslandMap(); }

    @Test
    public void IslandMapConstructorTest() throws InvalidMapException
    {
        assertEquals(1, map.getVerticalDimension());
        assertEquals(1, map.getHorizontalDimension());
        assertEquals(new Point(0,0), map.getDroneCoordinates());
        assertEquals(true, map.pointExist(new Point(0,0)));

        for(Direction direction:Direction.values())
        {
            assertEquals(0, map.getNumberOfAvailablePoints(direction));
            assertEquals(false, map.isDirectionFinished(direction));
        }

        assertEquals(false, map.isFinished());
        assertEquals("", map.getEmergencySiteId());

        for(Element element:Element.values())
            assertEquals(false, map.hasElement(new Point(0,0), element));

        assertEquals(1, map.getCreekIds(new Point(0,0)).length);
    }

    /**
     * On agrandie la carte pour ajouter un élément Ground
     */
    @Test
    public void GroundTest()
    {
        map.setGround(Direction.E, 10);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(11, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setGround(Direction.E, 11);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(12, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setGround(Direction.E, 0);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(12, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
    }

    @Test
    public void OutOfRangeTest()
    {
        map.setOutOfRange(Direction.E, 10);

        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.E, 10);

        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.N, 10);

        assertEquals(10, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.N, 10);

        assertEquals(10, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.S, 10);

        assertEquals(10, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.S, 10);

        assertEquals(10, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.W, 10);

        assertEquals(10, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.W));

        map.setOutOfRange(Direction.W, 10);

        assertEquals(10, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * le drone essaie de sortir de la carte
     */
    @Test (expected = InvalidMapException.class)
    public void MoveThenOutOfRangeTest() throws InvalidMapException { map.moveDrone(Direction.E); }

    @Test
    public void MoveTest() throws InvalidMapException
    {
        for(Direction direction:Direction.values())
        {
            map.setOutOfRange(direction, 10);
            assertEquals(10, map.getNumberOfAvailablePoints(direction));
            map.moveDrone(direction);
            assertEquals(9, map.getNumberOfAvailablePoints(direction));
        }
    }

    /**
     * On peut ajoutée un élément Ground puis un élément Out of range sans problème
     */
    @Test
    public void FoundTest() throws InvalidMapException {
        map.setGround(Direction.E, 10);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(11, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
        map.setOutOfRange(Direction.E, 50);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(50, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * Renvoie true si l'élément est présent à la position choisie, false sinon
     */
    @Test
    public void hasBiomeTest() throws InvalidMapException
    {
        for(Element element:Element.values())
            map.addBiomes(element);

        for(Element element:Element.values())
            assertTrue(map.hasElement(new Point(), element));
    }

    @Test
    public void hasNoBiomeTest() throws InvalidMapException
    {
        for(Element element:Element.values())
            assertFalse(map.hasElement(new Point(), element));
    }

    /**
     * Renvoie true si la carte a une dimension finie dans la direction choisie i.e ne peut plus être agrandie dans cette direction
     * Sinon, renvoie false
     */
    @Test
    public void isDirectionFinished() throws InvalidMapException
    {
        map.setOutOfRange(Direction.E, 10);
        map.setOutOfRange(Direction.N, 0);
        assertTrue(map.isDirectionFinished(Direction.N));
        assertTrue(map.isDirectionFinished(Direction.E));

        map.setGround(Direction.S, 10);

        assertFalse(map.isDirectionFinished(Direction.S));
        assertFalse(map.isDirectionFinished(Direction.W));

        map.setOutOfRange(Direction.S, 11);

        assertTrue(map.isDirectionFinished(Direction.S));
    }

    /**
     * Renvoie true si la carte a des dimensions finies dans toutes les directions, false sinon
     */
    @Test
    public void isFinished() throws InvalidMapException
    {
        map.setOutOfRange(Direction.E, 10);
        map.setOutOfRange(Direction.N, 0);
        assertFalse(map.isFinished());

        map.setOutOfRange(Direction.W, 10);
        map.setOutOfRange(Direction.S, 20);
        assertTrue(map.isFinished());
    }

    /**
     * On peut addElement plusieurs fois une direction sans retourner d'erreur si les valeurs coincide*
     */
    @Test
    public void setOutOfRangeExceptionTest() throws InvalidMapException
    {
        map.setOutOfRange(Direction.E, 1);
        map.setOutOfRange(Direction.E, 1);
        assertEquals(1, map.getNumberOfAvailablePoints(Direction.E));
    }

/**
 * on peut faire setOutOfRange et setGround du moment que c'est en position autorisée
 */
    @Test
    public void weirdTest() throws InvalidMapException
    {
        map.setOutOfRange(Direction.E, 1);
        map.setOutOfRange(Direction.W, 1);
        map.moveDrone(Direction.E);
        map.setGround(Direction.W, 1);
    }
}