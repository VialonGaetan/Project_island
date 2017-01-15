package areaPhase;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.DynamicMatrix;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
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

    /**
     * create an empty matrix
     */
    @Before
    public void before() { dynamicMatrix = new DynamicMatrix(); }

    /**
     * test init conditions
     */
    @Test
    public void DynamicMatrix() throws InvalidMapException
    {
        assertEquals(1, dynamicMatrix.getNumberOfLines());
        assertEquals(1, dynamicMatrix.getNumberOfColumns());
        assertEquals("", dynamicMatrix.get(0, 0));
    }

    /**
     * test init conditions
     * @throws InvalidMapException, if bad coordinates
     */
    @Test
    public void DynamicMatrixWithParameters() throws InvalidMapException
    {
        dynamicMatrix = new DynamicMatrix(10, 15);
        assertEquals(10, dynamicMatrix.getNumberOfLines());
        assertEquals(15, dynamicMatrix.getNumberOfColumns());

        for(int i=0; i<dynamicMatrix.getNumberOfLines(); i++)
        {
            for(int j=0; j<dynamicMatrix.getNumberOfColumns(); j++)
            {
                assertEquals("", dynamicMatrix.get(i, j));
            }
        }
    }

    /**
     * test if an element is correctly set
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void setElement() throws InvalidMapException
    {
        dynamicMatrix.setElement(0, 0, Element.MANGROVE.toString());
        assertEquals(Element.MANGROVE.toString(), dynamicMatrix.get(0, 0));
        dynamicMatrix.setElement(0, 0, Element.CREEK.toString());
        assertEquals(Element.CREEK.toString(), dynamicMatrix.get(0, 0));
    }

    /**
     * can't set an element out the matrix
     * @throws InvalidMapException, bad coordinates
     */
    @Test(expected=InvalidMapException.class)
    public void setElementException() throws InvalidMapException
    {
        dynamicMatrix.setElement(1, 0, Element.MANGROVE.toString());
    }

    /**
     * adds a new element
     * the old elements are not deleted
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addElement() throws InvalidMapException
    {
        dynamicMatrix.setElement(0, 0, Element.MANGROVE.toString());
        assertEquals(Element.MANGROVE.toString(), dynamicMatrix.get(0, 0));
        dynamicMatrix.addElement(0, 0, Element.CREEK.toString());
        assertTrue(dynamicMatrix.get(0, 0).contains(Element.MANGROVE.toString()));
        assertTrue(dynamicMatrix.get(0, 0).contains(Element.CREEK.toString()));
    }

    /**
     * can't add an element out the matrix
     * @throws InvalidMapException, bad coordinates
     */
    @Test(expected=InvalidMapException.class)
    public void addElementException() throws InvalidMapException
    {
        dynamicMatrix.addElement(1, 0, Element.MANGROVE.toString());
    }

    /**
     * get has to return an exception is bad coordinates
     * @throws InvalidMapException, bad coordinates
     */
    @Test(expected=InvalidMapException.class)
    public void getException() throws InvalidMapException
    {
        dynamicMatrix.get(1,0);
    }

    /**
     * test if a point exists
     */
    @Test
    public void pointExist()
    {
        assertTrue(dynamicMatrix.pointExist(0,0));
        assertFalse(dynamicMatrix.pointExist(0,1));
        assertFalse(dynamicMatrix.pointExist(1,0));
        assertFalse(dynamicMatrix.pointExist(1,-1));
    }

    /**
     * test if a matrix is correctly set as equal to another matrix
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void thisBecome() throws InvalidMapException
    {
        dynamicMatrix.setElement(0,0, Element.BEACH.toString());
        dynamicMatrix.addLines(-1, 2);
        dynamicMatrix.addColumns(0, 1);
        dynamicMatrix.setElement(0,0, Element.CREEK.toString());

        DynamicMatrix other=new DynamicMatrix();
        other.thisBecome(dynamicMatrix);

        assertEquals(dynamicMatrix.getNumberOfColumns(), other.getNumberOfColumns());
        assertEquals(dynamicMatrix.getNumberOfLines(), other.getNumberOfLines());

        assertEquals(Element.CREEK.toString(), other.get(0, 0));
        assertEquals(Element.BEACH.toString(), other.get(0, 1));

        for(int i=1; i<other.getNumberOfLines(); i++)
        {
            for(int j=0; j<other.getNumberOfColumns(); j++)
            {
                assertEquals("", other.get(i, j));
            }
        }
    }

    /**
     * addLines from a specific line into the matrix
     * test if number of lines added is correct
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addLinesNumber() throws InvalidMapException
    {
        dynamicMatrix.addLines(0, 2);
        assertEquals(3, dynamicMatrix.getNumberOfLines());
        dynamicMatrix.addLines(-1, 3);
        assertEquals(6, dynamicMatrix.getNumberOfLines());
    }

    /**
     * addLines from a specific line into the matrix
     * test if old elements are correctly moved
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addLinesElementAtEnd() throws InvalidMapException
    {
        dynamicMatrix.setElement(0, 0, Element.ALPINE.toString());
        dynamicMatrix.addLines(-1, 2);
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(0, 0));
        assertEquals("", dynamicMatrix.get(2, 0));
        dynamicMatrix.setElement(2, 0, Element.CREEK.toString());
        assertEquals(Element.CREEK.toString(), dynamicMatrix.get(2, 0));
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(0, 0));
    }

    /**
     * addLines from a specific line into the matrix
     * test if old elements are correctly moved
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addLinesElementAtBeginning() throws InvalidMapException
    {
        dynamicMatrix.setElement(0, 0, Element.ALPINE.toString());
        dynamicMatrix.addLines(0, 2);
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(2, 0));
        assertEquals("", dynamicMatrix.get(0, 0));
        dynamicMatrix.setElement(0, 0, Element.CREEK.toString());
        assertEquals(Element.CREEK.toString(), dynamicMatrix.get(0, 0));
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(2, 0));
    }

    /**
     * addColumns from a specific line into the matrix
     * test if number of columns added is correct
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addColumnsNumber() throws InvalidMapException
    {
        dynamicMatrix.addColumns(0, 2);
        assertEquals(3, dynamicMatrix.getNumberOfColumns());
        dynamicMatrix.addColumns(-1, 3);
        assertEquals(6, dynamicMatrix.getNumberOfColumns());
    }

    /**
     * addColumns from a specific line into the matrix
     * test if old elements are correctly moved
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addColumnsElementAtEnd() throws InvalidMapException
    {
        dynamicMatrix.setElement(0, 0, Element.ALPINE.toString());
        dynamicMatrix.addColumns(-1, 2);
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(0, 0));
        assertEquals("", dynamicMatrix.get(0, 2));
        dynamicMatrix.setElement(0, 2, Element.CREEK.toString());
        assertEquals(Element.CREEK.toString(), dynamicMatrix.get(0, 2));
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(0, 0));
    }

    /**
     * addColumns from a specific line into the matrix
     * test if old elements are correctly moved
     * @throws InvalidMapException, bad coordinates
     */
    @Test
    public void addColumnsElementAtBeginning() throws InvalidMapException
    {
        dynamicMatrix.setElement(0, 0, Element.ALPINE.toString());
        dynamicMatrix.addColumns(0, 2);
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(0, 2));
        assertEquals("", dynamicMatrix.get(0, 0));
        dynamicMatrix.setElement(0, 0, Element.CREEK.toString());
        assertEquals(Element.CREEK.toString(), dynamicMatrix.get(0, 0));
        assertEquals(Element.ALPINE.toString(), dynamicMatrix.get(0, 2));
    }
}
