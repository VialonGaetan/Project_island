package fr.unice.polytech.si3.qgl.iaad.util.resource;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public enum PrimaryResource implements Resource
{
    FISH(40, 1.05),
    QUARTZ(5, 0.7),
    ORE(15, 0.5),
    WOOD(40, 1.05),
    FRUITS(10, 1),
    SUGAR_CANE(20, 0.8),
    FLOWER(1, 0.2),
    FUR(5, 0.95);

    private final double perHectare;
    private final double difficulty;

    PrimaryResource(double perHectare, double difficulty)
    {
        this.perHectare = perHectare;
        this.difficulty = difficulty;
    }

    public double getPerHectare()
    {
        return perHectare;
    }

    public double getDifficulty()
    {
        return difficulty;
    }

    @Override
    public ResourceCategory getCategory()
    {
        return ResourceCategory.PRIMARY;
    }
}
