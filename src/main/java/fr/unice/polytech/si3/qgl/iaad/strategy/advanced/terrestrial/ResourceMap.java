package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ResourceMap
{
    private Map<PrimaryResource, Double> resources;

    ResourceMap(ResourceEntry... reagents)
    {
        resources = new EnumMap<>(PrimaryResource.class);

        for (ResourceEntry reagent : reagents)
        {
            resources.put(reagent.getResource(), reagent.getCoefficient());
        }
    }

    public Set<PrimaryResource> keySet()
    {
        return resources.keySet();
    }

    public boolean contains(PrimaryResource primaryResource)
    {
        return resources.containsKey(primaryResource);
    }

    public Double get(PrimaryResource primaryResource)
    {
        return resources.get(primaryResource);
    }
}
