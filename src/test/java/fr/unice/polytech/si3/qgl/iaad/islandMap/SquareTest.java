package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biomes;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author romain
 * Created on 02/02/17.
 */
public class SquareTest
{
    private Square square;

    @Before
    public void init() { square = new Square(); }

    @Test
    public void addElements()
    {
        square.addElements(Element.CREEK, Element.CREEK);
        assertEquals(square.getElements().length, 1);
        assertEquals(square.getElements()[0], Element.CREEK);
    }

    @Test
    public void getElements()
    {
        square.addElements(Element.CREEK, Element.EMERGENCY_SITE);
        assertEquals(square.getElements().length, 2);
        assertEquals(square.getElements()[0], Element.CREEK);
        assertEquals(square.getElements()[1], Element.EMERGENCY_SITE);
    }

    @Test
    public void addBiomes()
    {
        square.addBiomes(Biomes.ALPINE, Biomes.ALPINE);
        assertEquals(square.getBiomes().length, 1);
        assertEquals(square.getBiomes()[0], Biomes.ALPINE);
    }

    @Test
    public void getBiomes()
    {
        square.addBiomes(Biomes.ALPINE, Biomes.BEACH);
        assertEquals(square.getBiomes().length, 2);
        assertEquals(square.getBiomes()[0], Biomes.ALPINE);
        assertEquals(square.getBiomes()[1], Biomes.BEACH);
    }

    @Test
    public void addCreek()
    {
        square.addCreeks("id1", "id1");
        assertEquals(square.getCreekIds().length, 1);
        assertEquals(square.getCreekIds()[0], "id1");
    }

    @Test
    public void getCreekIds()
    {
        square.addCreeks("id1", "id2");
        assertEquals(square.getCreekIds().length, 2);
        assertEquals(square.getCreekIds()[0], "id1");
        assertEquals(square.getCreekIds()[1], "id2");
    }

    @Test
    public void deleteBiome()
    {
        square.addBiomes(Biomes.ALPINE, Biomes.BEACH);
        square.deleteBiomes(Biomes.ALPINE);

        assertEquals(square.getBiomes().length, 1);
        assertEquals(square.getBiomes()[0], Biomes.BEACH);
        square.deleteBiomes(Biomes.BEACH);
        assertEquals(square.getBiomes().length, 0);
        square.deleteBiomes(Biomes.BEACH);
        assertEquals(square.getBiomes().length, 0);
    }
}