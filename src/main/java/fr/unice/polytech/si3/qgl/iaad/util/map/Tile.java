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
    private final List<GroundActionTile> groundActionTiles;

    public Tile()
    {
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        emergencySites = new ArrayList<>();
        resourceInformationList = new ArrayList<>();
        exploitedResources = new ArrayList<>();
        groundActionTiles = new ArrayList<>();
    }

    public Tile(Tile tile)
    {
        biomes = tile.biomes;
        creeks = tile.creeks;
        emergencySites = tile.emergencySites;
        resourceInformationList = tile.resourceInformationList;
        exploitedResources = tile.exploitedResources;
        groundActionTiles = tile.groundActionTiles;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Tile tile = (Tile) o;

        return
        (
            biomes.equals(tile.biomes)
            && creeks.equals(tile.creeks)
            && emergencySites.equals(tile.emergencySites)
            && resourceInformationList.equals(tile.resourceInformationList)
            && exploitedResources.equals(tile.exploitedResources)
            && groundActionTiles.equals(tile.groundActionTiles)
        );
    }

    @Override
    public int hashCode()
    {
        int result = biomes.hashCode();

        result = 31 * result + creeks.hashCode();
        result = 31 * result + emergencySites.hashCode();
        result = 31 * result + resourceInformationList.hashCode();
        result = 31 * result + exploitedResources.hashCode();
        result = 31 * result + (groundActionTiles.hashCode());

        return result;
    }

    private <T> void add(List<T> list, List<T> toAdd) { toAdd.stream().filter(element -> !list.contains(element)).forEach(list::add); }

    public void exploitResource(Resource resource) { if(!exploitedResources.contains(resource)) exploitedResources.add(resource); }

    public boolean resourceAlreadyExploited(Resource resource) { return exploitedResources.contains(resource); }

    public void addBiomes(List<Biome> biomes) { add(this.biomes, biomes); }

    public void addResourceInformationList(List<ResourceInformation> resourceInformationList) { add(this.resourceInformationList, resourceInformationList); }

    public void addCreeks(List<Creek> creeks) { add(this.creeks, creeks); }

    public void addEmergencySites(List<EmergencySite> emergencySites) { add(this.emergencySites, emergencySites); }

    public List<Biome> getBiomes() { return biomes; }

    public List<Creek> getCreeks() { return creeks; }

    public List<EmergencySite> getEmergencySites() { return emergencySites; }

    public List<ResourceInformation> getResourceInformationList() { return resourceInformationList; }

    public void setAsAlready(GroundActionTile groundActionTile) { if(!groundActionTiles.contains(groundActionTile)) groundActionTiles.add(groundActionTile); }

    public boolean isAlready(GroundActionTile groundActionTile) { return groundActionTiles.contains(groundActionTile); }
}
