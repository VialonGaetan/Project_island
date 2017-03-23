package fr.unice.polytech.si3.qgl.iaad.util.resource;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ResourceEntry
{
    private final PrimaryResource resource;
    private final double coefficient;

    public ResourceEntry(PrimaryResource resource, double coefficient)
    {
        this.resource = resource;
        this.coefficient = coefficient;
    }

    public PrimaryResource getResource()
    {
        return resource;
    }

    public double getCoefficient()
    {
        return coefficient;
    }
}
