package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author romain
 * Created on 19/02/17.
 */
public class TileTest
{
    private Tile tile;

    @Before
    public void init() { tile = new Tile(); }

    @Test
    public void addBiomes()
    {
        List<Biome> biomes = new ArrayList<>();
        biomes.add(Biome.ALPINE);
        biomes.add(Biome.BEACH);
        tile.addBiomes(biomes);
        assertEquals(biomes, tile.getBiomes());
        List<Biome> biomes2 = new ArrayList<>(biomes);
        biomes2.add(Biome.BEACH);
        assertNotEquals(biomes2, tile.getBiomes());
        assertEquals(biomes, tile.getBiomes());
    }

    @Test
    public void addCreeks()
    {
        List<Creek> creeks = new ArrayList<>();
        creeks.add(new Creek("id1"));
        creeks.add(new Creek("id2"));
        tile.addCreeks(creeks);
        assertEquals(creeks, tile.getCreeks());
        List<Creek> creeks2 = new ArrayList<>(creeks);
        creeks2.add(new Creek("id2"));
        assertNotEquals(creeks2, tile.getCreeks());
        assertEquals(creeks, tile.getCreeks());
    }

    @Test
    public void addEmergencySites()
    {
        List<EmergencySite> emergencySites = new ArrayList<>();
        emergencySites.add(new EmergencySite("id1"));
        emergencySites.add(new EmergencySite("id2"));
        tile.addEmergencySites(emergencySites);;
        assertEquals(emergencySites, tile.getEmergencySites());
        List<EmergencySite> emergencySites2 = new ArrayList<>(emergencySites);
        emergencySites2.add(new EmergencySite("id2"));
        assertNotEquals(emergencySites2, tile.getEmergencySites());
        assertEquals(emergencySites, tile.getEmergencySites());
    }
}
