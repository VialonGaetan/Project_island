package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 14/01/2017.
 */
public class DynamicMatrixTest
{
    private DynamicMatrix dynamicMatrix;

    @Before
    public void before() { dynamicMatrix = new DynamicMatrix(); }

    @Test
    public void DynamicMatrix() throws InvalidMapException
    {
        assertEquals(1, dynamicMatrix.getNumberOfLines());
        assertEquals(1, dynamicMatrix.getNumberOfColumns());
    }

    @Test(expected=InvalidMapException.class)
    public void getSquareException() throws InvalidMapException
    {
        dynamicMatrix.getSquare(new Point(0, 1));
    }

    @Test
    public void pointExist()
    {
        assertTrue(dynamicMatrix.pointExist(new Point()));
        assertFalse(dynamicMatrix.pointExist(new Point(1, 0)));
        assertFalse(dynamicMatrix.pointExist(new Point(0, 1)));
        assertFalse(dynamicMatrix.pointExist(new Point(-1, 1)));
    }

    @Test
    public void addLinesNumber() throws InvalidMapException
    {
        dynamicMatrix.addLines(0, 2);
        assertEquals(3, dynamicMatrix.getNumberOfLines());
        dynamicMatrix.addLines(-1, 3);
        assertEquals(6, dynamicMatrix.getNumberOfLines());
    }


    @Test
    public void addLinesElementAtEnd() throws InvalidMapException
    {
        dynamicMatrix.getSquare(new Point()).addBiomes(Biome.ALPINE);
        dynamicMatrix.addLines(-1, 2);

        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point()).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(new Point(0, 2)).getBiomes().length);

        dynamicMatrix.getSquare(new Point(0, 2)).addElement(Element.CREEK);

        assertEquals(Element.CREEK, dynamicMatrix.getSquare(new Point(0, 2)).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point()).getBiomes()[0]);
    }

    @Test
    public void addLinesElementAtBeginning() throws InvalidMapException
    {
        dynamicMatrix.getSquare(new Point()).addBiomes(Biome.ALPINE);
        dynamicMatrix.addLines(0, 2);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point(0, 2)).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(new Point()).getBiomes().length);

        dynamicMatrix.getSquare(new Point()).addElement(Element.CREEK);
        assertEquals(Element.CREEK, dynamicMatrix.getSquare(new Point()).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point(0, 2)).getBiomes()[0]);
    }

    @Test
    public void addColumnsNumber() throws InvalidMapException
    {
        dynamicMatrix.addColumns(0, 2);
        assertEquals(3, dynamicMatrix.getNumberOfColumns());
        dynamicMatrix.addColumns(-1, 3);
        assertEquals(6, dynamicMatrix.getNumberOfColumns());
    }

    @Test
    public void addColumnsElementAtEnd() throws InvalidMapException
    {
        dynamicMatrix.getSquare(new Point()).addBiomes(Biome.ALPINE);
        dynamicMatrix.addColumns(-1, 2);

        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point()).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(new Point(2, 0)).getBiomes().length);

        dynamicMatrix.getSquare(new Point(2, 0)).addElement(Element.CREEK);

        assertEquals(Element.CREEK, dynamicMatrix.getSquare(new Point(2, 0)).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point()).getBiomes()[0]);
    }

    @Test
    public void addColumnsElementAtBeginning() throws InvalidMapException
    {
        dynamicMatrix.getSquare(new Point()).addBiomes(Biome.ALPINE);
        dynamicMatrix.addColumns(0, 2);

        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point(2, 0)).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(new Point()).getBiomes().length);

        dynamicMatrix.getSquare(new Point()).addElement(Element.CREEK);

        assertEquals(Element.CREEK, dynamicMatrix.getSquare(new Point()).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(new Point(2, 0)).getBiomes()[0]);
    }
}
