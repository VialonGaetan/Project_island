package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void updateSquare() throws InvalidMapException
    {
        Square square = new Square();

        square.addBiomes(Biome.ALPINE);
        dynamicMatrix.updateSquare(0, 0, square);

        assertNotEquals(square, dynamicMatrix.getSquare(0, 0));
        assertEquals(dynamicMatrix.getSquare(0, 0).getBiomes()[0], Biome.ALPINE);
    }

    @Test(expected=InvalidMapException.class)
    public void updateSquareException() throws InvalidMapException
    {
        Square square = new Square();
        square.addBiomes(Biome.ALPINE);
        dynamicMatrix.updateSquare(1, 0, square);
    }

    @Test(expected=InvalidMapException.class)
    public void getSquareException() throws InvalidMapException
    {
        dynamicMatrix.getSquare(1,0);
    }

    @Test
    public void pointExist()
    {
        assertTrue(dynamicMatrix.pointExist(0,0));
        assertFalse(dynamicMatrix.pointExist(0,1));
        assertFalse(dynamicMatrix.pointExist(1,0));
        assertFalse(dynamicMatrix.pointExist(1,-1));
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
        Square square = new Square();
        square.addBiomes(Biome.ALPINE);

        dynamicMatrix.updateSquare(0, 0, square);
        dynamicMatrix.addLines(-1, 2);

        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(0, 0).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(2, 0).getBiomes().length);

        square.addElement(Element.CREEK);
        dynamicMatrix.updateSquare(2, 0, square);

        assertEquals(Element.CREEK, dynamicMatrix.getSquare(2, 0).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(0, 0).getBiomes()[0]);
    }

    @Test
    public void addLinesElementAtBeginning() throws InvalidMapException
    {
        Square square = new Square();
        square.addBiomes(Biome.ALPINE);

        dynamicMatrix.updateSquare(0, 0, square);
        dynamicMatrix.addLines(0, 2);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(2, 0).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(0, 0).getBiomes().length);

        square.addElement(Element.CREEK);
        dynamicMatrix.updateSquare(0, 0, square);
        assertEquals(Element.CREEK, dynamicMatrix.getSquare(0, 0).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(2, 0).getBiomes()[0]);
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
        Square square = new Square();
        square.addBiomes(Biome.ALPINE);

        dynamicMatrix.updateSquare(0, 0, square);
        dynamicMatrix.addColumns(-1, 2);

        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(0, 0).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(0, 2).getBiomes().length);

        square.addElement(Element.CREEK);
        dynamicMatrix.updateSquare(0, 2, square);

        assertEquals(Element.CREEK, dynamicMatrix.getSquare(0, 2).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(0, 0).getBiomes()[0]);
    }

    @Test
    public void addColumnsElementAtBeginning() throws InvalidMapException
    {
        Square square = new Square();
        square.addBiomes(Biome.ALPINE);

        dynamicMatrix.updateSquare(0, 0, square);
        dynamicMatrix.addColumns(0, 2);

        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(0, 2).getBiomes()[0]);
        assertEquals(0, dynamicMatrix.getSquare(0, 0).getBiomes().length);

        square.addElement(Element.CREEK);
        dynamicMatrix.updateSquare(0, 0, square);

        assertEquals(Element.CREEK, dynamicMatrix.getSquare(0, 0).getElements()[0]);
        assertEquals(Biome.ALPINE, dynamicMatrix.getSquare(0, 2).getBiomes()[0]);
    }
}
