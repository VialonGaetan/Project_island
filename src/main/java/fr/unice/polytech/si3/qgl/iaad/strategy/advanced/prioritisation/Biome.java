package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public enum Biome
{
    MANGROVE(1.8, new ResourceEntry(PrimaryResource.WOOD, 0.6), new ResourceEntry(PrimaryResource.FLOWER, 0.4)),
    SNOW(1.2),
    TROPICAL_RAIN_FOREST(1.8, new ResourceEntry(PrimaryResource.WOOD, 0.4), new ResourceEntry(PrimaryResource.SUGAR_CANE, 0.4), new ResourceEntry(PrimaryResource.FRUITS, 0.2)),
    TROPICAL_SEASONAL_FOREST(1.4, new ResourceEntry(PrimaryResource.WOOD, 0.4), new ResourceEntry(PrimaryResource.SUGAR_CANE, 0.5), new ResourceEntry(PrimaryResource.FRUITS, 0.1)),
    TAIGA(1.3, new ResourceEntry(PrimaryResource.WOOD, 1)),
    TEMPERATE_RAIN_FOREST(1.2, new ResourceEntry(PrimaryResource.WOOD, 0.8), new ResourceEntry(PrimaryResource.FUR, 0.2)),
    TEMPERATE_DECIDUOUS_FOREST(1.2, new ResourceEntry(PrimaryResource.WOOD, 1)),
    GRASSLAND(0.8, new ResourceEntry(PrimaryResource.FUR, 1)),
    SHRUBLAND(0.8, new ResourceEntry(PrimaryResource.FUR, 1)),
    TUNDRA(0.9, new ResourceEntry(PrimaryResource.FUR, 1)),
    ALPINE(2.5, new ResourceEntry(PrimaryResource.ORE, 0.2), new ResourceEntry(PrimaryResource.FLOWER, 0.05)),
    BEACH(0.9, new ResourceEntry(PrimaryResource.QUARTZ, 0.2)),
    SUB_TROPICAL_DESERT(1.1, new ResourceEntry(PrimaryResource.ORE, 0.2), new ResourceEntry(PrimaryResource.QUARTZ, 0.4)),
    TEMPERATE_DESERT(1.1, new ResourceEntry(PrimaryResource.ORE, 0.3), new ResourceEntry(PrimaryResource.QUARTZ, 0.3)),
    OCEAN(3.0, new ResourceEntry(PrimaryResource.FISH, 0.9)),
    LAKE(2.0, new ResourceEntry(PrimaryResource.FISH, 0.8)),
    GLACIER(2.5, new ResourceEntry(PrimaryResource.FLOWER, 0.05));

    private final double crossFactor;
    private final ResourceMap resourceMap;

    Biome(double crossFactor, ResourceEntry... coefficientResources)
    {
        this.crossFactor = crossFactor;
        resourceMap = new ResourceMap(coefficientResources);
    }

    public double getCrossFactor()
    {
        return crossFactor;
    }

    public ResourceMap getResourceMap()
    {
        return resourceMap;
    }
}
