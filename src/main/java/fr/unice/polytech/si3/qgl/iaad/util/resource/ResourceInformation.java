package fr.unice.polytech.si3.qgl.iaad.util.resource;


public class ResourceInformation
{
    private final PrimaryResource resource;
    private final ResourceAmount resourceAmount;
    private final ResourceCondition resourceCondition;

    public ResourceInformation(PrimaryResource resource, ResourceAmount resourceAmount, ResourceCondition resourceCondition)
    {
        this.resource = resource;
        this.resourceAmount = resourceAmount;
        this.resourceCondition = resourceCondition;
    }

    public PrimaryResource getResource()
    {
        return resource;
    }

    public ResourceAmount getResourceAmount()
    {
        return resourceAmount;
    }

    public ResourceCondition getResourceCondition()
    {
        return resourceCondition;
    }
}
