package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public enum Manufactured implements ResourceCategorised
{
    GLASS(new ResourceMap(new ResourceEntry(PrimaryResource.QUARTZ, 10), new ResourceEntry(PrimaryResource.WOOD, 5)), 0.5, false),
    INGOT(new ResourceMap(new ResourceEntry(PrimaryResource.ORE, 5), new ResourceEntry(PrimaryResource.WOOD, 5)), 1, true),
    PLANK(new ResourceMap(new ResourceEntry(PrimaryResource.WOOD, 0.25)), 0.1, false),
    LEATHER(new ResourceMap(new ResourceEntry(PrimaryResource.FUR, 3)), 1.2, false),
    RUM(new ResourceMap(new ResourceEntry(PrimaryResource.SUGAR_CANE, 10), new ResourceEntry(PrimaryResource.FRUITS, 1)), 3, true);

    private final ResourceMap recipe;
    private final double factor;
    private final boolean isAloneActivity;

    Manufactured(ResourceMap recipe, double factor, boolean isAloneActivity)
    {
        this.recipe = recipe;
        this.factor = factor;
        this.isAloneActivity = isAloneActivity;
    }

    public ResourceMap getRecipe()
    {
        return recipe;
    }

    public double getFactor()
    {
        return factor;
    }

    public boolean isAloneActivity()
    {
        return isAloneActivity;
    }


    @Override
    public ResourceCategory getCategory()
    {
        return ResourceCategory.MANUFACTURED;
    }
}
