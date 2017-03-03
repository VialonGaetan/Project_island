package fr.unice.polytech.si3.qgl.iaad.util.map;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author romain
 * Created on 14/02/17.
 */
public class Tile
{
    private List<Biome> biomes;
    private List<Creek> creeks;
    private List<EmergencySite> emergencySites;
    private List<ResourceInformation> resourceInformations;
    private boolean isAlreadyScanned, isAlreadyVisited, isAlreadyScouted, isAlreadyGlimpsed;

    public Tile()
    {
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        emergencySites = new ArrayList<>();
        resourceInformations = new ArrayList<>();
    }

    public Tile(Tile tile)
    {
        biomes = new ArrayList<>(tile.biomes);
        creeks = new ArrayList<>(tile.creeks);
        emergencySites = new ArrayList<>(tile.emergencySites);
        resourceInformations = new ArrayList<>(tile.resourceInformations);
        isAlreadyVisited = tile.isAlreadyVisited;
        isAlreadyScanned = tile.isAlreadyScanned;
        isAlreadyScouted = tile.isAlreadyScouted;
        isAlreadyGlimpsed = tile.isAlreadyGlimpsed;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Tile tile = (Tile) o;
        boolean test = false;

        if(biomes.equals(tile.biomes) && creeks.equals(tile.creeks) && emergencySites.equals(tile.emergencySites) && resourceInformations.equals(tile.resourceInformations))
        {
            if(isAlreadyVisited == tile.isAlreadyVisited && isAlreadyScanned == tile.isAlreadyScanned && isAlreadyScouted == tile.isAlreadyScouted && isAlreadyGlimpsed == tile.isAlreadyGlimpsed)
            {
                test = true;
            }
        }

        return test;
    }

    public void addBiomes(List<Biome> biomes)
    {
        for(Biome biome : biomes)
        {
            addBiome(biome);
        }
    }

    public void addResourceInformations(List<ResourceInformation> resourceInformations)
    {
        for(ResourceInformation resourceInformation : resourceInformations)
        {
            addResourceInformation(resourceInformation);
        }
    }

    public void addCreeks(List<Creek> creeks)
    {
        for(Creek creek : creeks)
        {
            addCreek(creek);
        }
    }

    public void addEmergencySites(List<EmergencySite> emergencySites)
    {
        for(EmergencySite emergencySite : emergencySites)
        {
            addEmergencySite(emergencySite);
        }
    }

    public List<Biome> getBiomes() { return biomes; }

    public List<Creek> getCreeks() { return creeks; }

    public List<EmergencySite> getEmergencySites() { return emergencySites; }

    public List<ResourceInformation> getResourceInformations() { return resourceInformations; }

    public boolean isAlreadyScanned() { return isAlreadyScanned; }

    public boolean isAlreadyVisited() { return isAlreadyVisited; }

    public boolean isAlreadyScouted() { return isAlreadyScouted; }

    public boolean isAlreadyGlimpsed() { return isAlreadyGlimpsed; }

    public void setAsAlreadyScanned() { isAlreadyScanned = true; }

    public void setAsAlreadyVisited() { isAlreadyVisited = true; }

    public void setAsAlreadyScouted() { isAlreadyScouted = true; }

    public void setAsAlreadyGlimpsed() { isAlreadyGlimpsed = true; }

    private void addBiome(Biome biome)
    {
        for(Biome current : biomes)
        {
            if(current == biome)
            {
                return;
            }
        }

        biomes.add(biome);
    }

    private void addCreek(Creek creek)
    {
        for(Creek current : creeks)
        {
            if(current.equals(creek))
            {
                return;
            }
        }

        creeks.add(creek);
    }

    private void addEmergencySite(EmergencySite emergencySite)
    {
        for(EmergencySite current : emergencySites)
        {
            if(current.equals(emergencySite))
            {
                return;
            }
        }

        emergencySites.add(emergencySite);
    }

    private void addResourceInformation(ResourceInformation resourceInformation)
    {
        for(ResourceInformation current : resourceInformations)
        {
            if(current.equals(resourceInformation))
            {
                return;
            }
        }

        resourceInformations.add(resourceInformation);
    }
}
