package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biome;

import java.util.ArrayList;
import java.util.List;

/**
 * @author romain
 * Created on 14/02/17.
 */
public class Tile implements fr.unice.polytech.si3.qgl.iaad.future.Tile
{
    private List<Biome> biomes;
    private List<Creek> creeks;
    private List<EmergencySite> emergencySites;
    private boolean isAlreadyScanned;
    private boolean isAlreadyVisited;
    private boolean isAlreadyScouted;
    private boolean isAlreadyGlimpsed;

    public Tile()
    {
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        emergencySites = new ArrayList<>();
    }

    public Tile(Tile tile)
    {
        biomes = new ArrayList<>(tile.biomes);
        creeks = new ArrayList<>(tile.creeks);
        emergencySites = new ArrayList<>(tile.emergencySites);
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

        if(biomes.equals(tile.biomes) && creeks.equals(tile.creeks) && emergencySites.equals(tile.emergencySites))
        {
            if(isAlreadyVisited == tile.isAlreadyVisited && isAlreadyScanned == tile.isAlreadyScanned && isAlreadyScouted == tile.isAlreadyScouted && isAlreadyGlimpsed == tile.isAlreadyGlimpsed)
            {
                test = true;
            }
        }

        return test;
    }

    @Override
    public void addBiomes(List<Biome> biomes)
    {
        for(Biome biome : biomes)
        {
            addBiome(biome);
        }
    }

    @Override
    public void addCreeks(List<Creek> creeks)
    {
        for(Creek creek : creeks)
        {
            addCreek(creek);
        }
    }

    @Override
    public void addEmergencySites(List<EmergencySite> emergencySites)
    {
        for(EmergencySite emergencySite : emergencySites)
        {
            addEmergencySite(emergencySite);
        }
    }

    @Override
    public List<Biome> getBiomes() { return biomes; }

    @Override
    public List<Creek> getCreeks() { return creeks; }

    @Override
    public List<EmergencySite> getEmergencySites() { return emergencySites; }

    @Override
    public boolean isAlreadyScanned() { return isAlreadyScanned; }

    @Override
    public boolean isAlreadyVisited() { return isAlreadyVisited; }

    @Override
    public boolean isAlreadyScouted() { return isAlreadyScouted; }

    @Override
    public boolean isAlreadyGlimpsed() { return isAlreadyGlimpsed; }

    @Override
    public void setAsAlreadyScanned() { isAlreadyScanned = true; }

    @Override
    public void setAsAlreadyVisited() { isAlreadyVisited = true; }

    @Override
    public void setAsAlreadyScouted() { isAlreadyScouted = true; }

    @Override
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
}
