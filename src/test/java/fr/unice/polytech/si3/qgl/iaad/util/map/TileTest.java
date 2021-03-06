package fr.unice.polytech.si3.qgl.iaad.util.map;

import fr.unice.polytech.si3.qgl.iaad.util.resource.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 19/02/17.
 */
public class TileTest
{
    private Tile tile;

    @Before
    public void init() { tile = new Tile(); }

    /**
     * test if an init tile is empty and not visited yet
     */
    @Test
    public void constructor()
    {
        assertTrue(tile.getBiomes().isEmpty());
        assertTrue(tile.getCreeks().isEmpty());
        assertTrue(tile.getEmergencySites().isEmpty());
        assertTrue(tile.getResourceInformationList().isEmpty());

        for(GroundActionTile groundActionTile : GroundActionTile.values())
        {
            assertFalse(tile.isAlready(groundActionTile));
        }

        for(PrimaryResource resource : PrimaryResource.values())
        {
            assertFalse(tile.resourceAlreadyExploited(resource));
        }
    }

    /**
     * test the new Tile(tile) constructor
     * the tile must be the equals to the other but not the same (not referenced)
     */
    @Test
    public void constructor2()
    {
        List<Biome> biomes = new ArrayList<>(), biomes2;

        biomes.add(Biome.ALPINE);
        biomes2 = new ArrayList<>(biomes);

        this.tile.setAsAlready(GroundActionTile.SCANNED);
        this.tile.addBiomes(biomes);

        Tile tile = new Tile(this.tile);
        assertTrue(this.tile.equals(tile));

        biomes.add(Biome.BEACH);
        assertFalse(tile.getBiomes().equals(biomes));
        assertTrue(tile.getBiomes().equals(biomes2));
    }

    /**
     * test if a tile is really equal to another
     */
    @Test
    public void equalsTest()
    {
        List<Biome> biomes = new ArrayList<>();
        List<Creek> creeks = new ArrayList<>();
        List<EmergencySite> emergencySites = new ArrayList<>();
        List<ResourceInformation> resourceInformationList = new ArrayList<>();

        biomes.add(Biome.BEACH);
        creeks.add(new Creek("creek"));
        emergencySites.add(new EmergencySite("em"));
        resourceInformationList.add(new ResourceInformation(PrimaryResource.FISH, ResourceAmount.HIGH, ResourceCondition.EASY));

        tile.addBiomes(biomes);
        tile.addCreeks(creeks);
        tile.addEmergencySites(emergencySites);
        tile.addResourceInformationList(resourceInformationList);
        tile.exploitResource(PrimaryResource.FISH);

        assertFalse(tile.equals(new Tile()));

        Tile tile = new Tile(this.tile);
        assertTrue(tile.equals(this.tile));
    }

    @Test
    public void hashCodeTest() { assertEquals(29583456, tile.hashCode()); }

    /**
     * test if a resource has already been exploited
     */
    @Test
    public void resourceAlreadyExploited()
    {
        for(PrimaryResource resource : PrimaryResource.values())
        {
            assertFalse(tile.resourceAlreadyExploited(resource));
            tile.exploitResource(resource);
            assertTrue(tile.resourceAlreadyExploited(resource));
        }
    }

    /**
     * test all the add methods as addEmergencySite, addCreeks, etc ...
     */
    @Test
    public void add()
    {
        List<Biome> biomes = new ArrayList<>();
        List<Creek> creeks = new ArrayList<>();
        List<EmergencySite> emergencySites = new ArrayList<>();
        List<ResourceInformation> resourceInformationList = new ArrayList<>();

        biomes.add(Biome.ALPINE);
        biomes.add(Biome.BEACH);
        creeks.add(new Creek("id1"));
        creeks.add(new Creek("id2"));
        emergencySites.add(new EmergencySite("id1"));
        emergencySites.add(new EmergencySite("id2"));
        resourceInformationList.add(new ResourceInformation(PrimaryResource.FISH, ResourceAmount.HIGH, ResourceCondition.EASY));
        resourceInformationList.add(new ResourceInformation(PrimaryResource.FLOWER, ResourceAmount.HIGH, ResourceCondition.EASY));


        tile.addBiomes(biomes);
        tile.addCreeks(creeks);
        tile.addEmergencySites(emergencySites);
        tile.addResourceInformationList(resourceInformationList);

        assertEquals(biomes, tile.getBiomes());
        assertEquals(creeks, tile.getCreeks());
        assertEquals(emergencySites, tile.getEmergencySites());
        assertEquals(resourceInformationList, tile.getResourceInformationList());

        List<Biome> biomes2 = new ArrayList<>(biomes);
        List<Creek> creeks2 = new ArrayList<>(creeks);
        List<EmergencySite> emergencySites2 = new ArrayList<>(emergencySites);
        List<ResourceInformation> resourceInformationList2 = new ArrayList<>(resourceInformationList);

        biomes2.add(Biome.BEACH);
        creeks2.add(new Creek("id2"));
        emergencySites2.add(new EmergencySite("id2"));
        resourceInformationList2.add(new ResourceInformation(PrimaryResource.FLOWER, ResourceAmount.LOW, ResourceCondition.EASY));

        assertNotEquals(biomes2, tile.getBiomes());
        assertNotEquals(creeks2, tile.getCreeks());
        assertNotEquals(emergencySites2, tile.getEmergencySites());
        assertNotEquals(resourceInformationList2, tile.getResourceInformationList());

        assertEquals(biomes, tile.getBiomes());
        assertEquals(creeks, tile.getCreeks());
        assertEquals(emergencySites, tile.getEmergencySites());
        assertEquals(resourceInformationList, tile.getResourceInformationList());
    }

    /**
     * test all the getters
     */
    @Test
    public void get()
    {
        List<Biome> biomes = new ArrayList<>();
        List<Creek> creeks = new ArrayList<>();
        List<EmergencySite> emergencySites = new ArrayList<>();
        List<ResourceInformation> resourceInformationList = new ArrayList<>();

        biomes.add(Biome.BEACH);
        creeks.add(new Creek("creek"));
        emergencySites.add(new EmergencySite("em"));
        resourceInformationList.add(new ResourceInformation(PrimaryResource.FISH, ResourceAmount.HIGH, ResourceCondition.EASY));

        tile.addBiomes(biomes);
        tile.addCreeks(creeks);
        tile.addEmergencySites(emergencySites);
        tile.addResourceInformationList(resourceInformationList);

        assertTrue(tile.getBiomes().equals(biomes));
        assertTrue(tile.getEmergencySites().equals(emergencySites));
        assertTrue(tile.getCreeks().equals(creeks));
        assertTrue(tile.getResourceInformationList().equals(resourceInformationList));
    }

    /**
     * test all the actions done on the tile
     */
    @Test
    public void isAlready()
    {
        for(GroundActionTile groundActionTile : GroundActionTile.values())
        {
            assertFalse(tile.isAlready(groundActionTile));
            tile.setAsAlready(groundActionTile);
            assertTrue(tile.isAlready(groundActionTile));
        }
    }
}
