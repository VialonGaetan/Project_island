package fr.unice.polytech.si3.qgl.iaad.util.map;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;

import java.util.List;
import java.util.ArrayList;

/**
 * @author romain
 * Created on 14/02/17.
 */
public class Tile
{
    private final List<Biome> biomes;
    private final List<Creek> creeks;
    private final List<EmergencySite> emergencySites;
    private final List<ResourceInformation> resourceInformationList;
    private final List<Resource> exploitedResources;
    private boolean isAlreadyScanned, isAlreadyVisited, isAlreadyScouted, isAlreadyGlimpsed;

     public Tile()
    {
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        emergencySites = new ArrayList<>();
        resourceInformationList = new ArrayList<>();
        exploitedResources = new ArrayList<>();
    }

    public Tile(Tile tile)
    {
        biomes = new ArrayList<>(tile.biomes);
        creeks = new ArrayList<>(tile.creeks);
        emergencySites = new ArrayList<>(tile.emergencySites);
        resourceInformationList = new ArrayList<>(tile.resourceInformationList);
        exploitedResources = new ArrayList<>(tile.exploitedResources);
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

        if(biomes.equals(tile.biomes) && creeks.equals(tile.creeks) && emergencySites.equals(tile.emergencySites) && resourceInformationList.equals(tile.resourceInformationList) && exploitedResources.equals(tile.exploitedResources))
        {
            if(isAlreadyVisited == tile.isAlreadyVisited && isAlreadyScanned == tile.isAlreadyScanned && isAlreadyScouted == tile.isAlreadyScouted && isAlreadyGlimpsed == tile.isAlreadyGlimpsed)
            {
                test = true;
            }
        }

        return test;
    }

    @Override
    public int hashCode()
    {
        int result = biomes.hashCode();

        result = 31 * result + creeks.hashCode();
        result = 31 * result + emergencySites.hashCode();
        result = 31 * result + resourceInformationList.hashCode();
        result = 31 * result + exploitedResources.hashCode();
        result = 31 * result + (isAlreadyScanned ? 1 : 0);
        result = 31 * result + (isAlreadyVisited ? 1 : 0);
        result = 31 * result + (isAlreadyScouted ? 1 : 0);
        result = 31 * result + (isAlreadyGlimpsed ? 1 : 0);

        return result;
    }

    private <T> void add(List<T> list, List<T> toAdd) { toAdd.stream().filter(element -> !list.contains(element)).forEach(list::add); }

    public void exploitResource(Resource resource) { if(!exploitedResources.contains(resource)) exploitedResources.add(resource); }

    public boolean resourceAlreadyExploited(Resource resource) { return exploitedResources.contains(resource); }

    public void addBiomes(List<Biome> biomes) { add(this.biomes, biomes); }

    public void addResourceInformations(List<ResourceInformation> resourceInformationList) { add(this.resourceInformationList, resourceInformationList); }

    public void addCreeks(List<Creek> creeks) { add(this.creeks, creeks); }

    public void addEmergencySites(List<EmergencySite> emergencySites) { add(this.emergencySites, emergencySites); }

    public List<Biome> getBiomes() { return biomes; }

    public List<Creek> getCreeks() { return creeks; }

    public List<EmergencySite> getEmergencySites() { return emergencySites; }

    public List<ResourceInformation> getResourceInformationList() { return resourceInformationList; }

    public boolean isAlreadyScanned() { return isAlreadyScanned; }

    public boolean isAlreadyVisited() { return isAlreadyVisited; }

    public boolean isAlreadyScouted() { return isAlreadyScouted; }

    public boolean isAlreadyGlimpsed() { return isAlreadyGlimpsed; }

    public void setAsAlreadyScanned() { isAlreadyScanned = true; }

    public void setAsAlreadyVisited() { isAlreadyVisited = true; }

    public void setAsAlreadyScouted() { isAlreadyScouted = true; }

    public void setAsAlreadyGlimpsed() { isAlreadyGlimpsed = true; }
}
