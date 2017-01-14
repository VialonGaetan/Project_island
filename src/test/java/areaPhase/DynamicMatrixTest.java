package areaPhase;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.DynamicMatrix;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 14/01/2017.
 */
public class DynamicMatrixTest
{
    private DynamicMatrix dynamicMatrix;

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
     * @throws InvalidMapException
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
     * adds a new element
     * the old elements are not deleted
     * @throws InvalidMapException
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
     * get has to return an exception is bad coordinates
     * @throws InvalidMapException
     */
    @Test(expected=InvalidMapException.class)
    public void getException() throws InvalidMapException
    {
        String string = dynamicMatrix.get(1,0);
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
}
