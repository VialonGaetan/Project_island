package fr.unice.polytech.si3.qgl.iaad;

import java.util.Arrays;
import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public enum Biome {
    OCEAN(Resource.FISH),
    LAKE(Resource.FISH),
    BEACH(Resource.QUARTZ),
    GRASSLAND(Resource.FUR),
    MANGROVE(Resource.FLOWER, Resource.WOOD),
    TROPICAL_RAIN_FOREST(Resource.FRUITS, Resource.SUGAR_CANE),
    TROPICAL_SEASONAL_FOREST(Resource.FRUITS, Resource.SUGAR_CANE),
    TEMPERATE_DECIDUOUS_FOREST(Resource.WOOD),
    TEMPERATE_RAIN_FOREST(Resource.WOOD),
    TEMPERATE_DESERT(Resource.ORE, Resource.QUARTZ),
    TAIGA(),
    SNOW(Resource.ORE),
    ALPINE(Resource.FLOWER, Resource.ORE),
    GLACIER(Resource.FLOWER),
    SHRUBLAND(Resource.FUR),
    SUB_TROPICAL_DESERT(Resource.ORE, Resource.QUARTZ);

    private List<Resource> resources;

    Biome(Resource... resources) {
        this.resources = Arrays.asList(resources);
    }

    public  List<Resource> getAssociateResources(Biome biome) {
        return biome.resources;
    }
    public  List<Resource> getAssociateResources() {
        return this.resources;
    }
}
