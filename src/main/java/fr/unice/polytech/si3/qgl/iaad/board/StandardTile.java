package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.EmergencySite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 15/02/2017.
 */
public class StandardTile implements Tile
{
    private List<Biome> biomes;
    private List<Creek> creeks;
    private List<EmergencySite> emergencySites;
    private boolean alreadyScanned;
    private boolean alreadyVisited;
    private boolean alreadyScouted;
    private boolean alreadyGlimpsed;

    StandardTile()
    {
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        emergencySites = new ArrayList<>();
        alreadyGlimpsed = false;
        alreadyScanned = false;
        alreadyScouted = false;
        alreadyVisited = false;
    }

    @Override
    public void addBiomes(List<Biome> biomes)
    {
        this.biomes.addAll(biomes);
    }

    @Override
    public void addCreeks(List<Creek> creeks)
    {
        this.creeks.addAll(creeks);
    }

    @Override
    public void addEmergencySites(List<EmergencySite> emergencySites)
    {
        this.emergencySites.addAll(emergencySites);
    }

    @Override
    public List<Biome> getBiomes()
    {
        return biomes;
    }

    @Override
    public List<Creek> getCreeks()
    {
        return creeks;
    }

    @Override
    public List<EmergencySite> getEmergencySites()
    {
        return emergencySites;
    }

    @Override
    public boolean isAlreadyScanned()
    {
        return alreadyScanned;
    }

    @Override
    public void scan()
    {
        alreadyScanned = true;
    }

    @Override
    public boolean isAlreadyVisited()
    {
        return alreadyVisited;
    }

    @Override
    public void visit()
    {
        alreadyVisited = true;
    }

    @Override
    public boolean isAlreadyScouted()
    {
        return alreadyScouted;
    }

    @Override
    public void scout()
    {
        alreadyScouted = true;
    }

    @Override
    public boolean isAlreadyGlimpsed()
    {
        return alreadyGlimpsed;
    }

    @Override
    public void glimpse()
    {
        alreadyGlimpsed = true;
    }

    @Override
    public String toString()
    {
        return String.format("Tile{%s, %s, %s}", biomes, creeks, emergencySites);
    }
}
