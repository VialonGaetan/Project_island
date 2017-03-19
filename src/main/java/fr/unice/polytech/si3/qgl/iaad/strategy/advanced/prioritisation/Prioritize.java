package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class Prioritize
{
    private final ModeledMap map;
    private final Contract contract;

    public Prioritize(ModeledMap map, Contract contract)
    {
        this.map = map;
        this.contract = contract;
    }

    private double getDifficulty(PrimaryResource primaryResource)
    {
        return primaryResource.getPerHectare() * map.getComputedArea(primaryResource) / primaryResource.getDifficulty();
    }

    double getPriority(PrimaryResource primaryResource)
    {
        return getDifficulty(primaryResource) / getAmount(Resource.valueOf(primaryResource.toString()));
    }

    double getPriority(Manufactured manufactured)
    {
        return manufactured.getRecipe().keySet().stream().mapToDouble(this::getDifficulty).min().orElse(0) / manufactured.getFactor() / getAmount(Resource.valueOf(manufactured.toString()));
    }

    private Integer getAmount(Resource key)
    {
        if (contract.completed(key))
            return -1;
        return contract.getOrDefault(key, -1);
    }

    private double getPriority(Resource resource)
    {
        if (resource.isPrimary())
            return getPriority(PrimaryResource.valueOf(resource.toString()));
        return getPriority(Manufactured.valueOf(resource.toString()));
    }

    public Map<Resource, Double> getPrioritizedContract()
    {
        return contract.keySet().stream().collect(Collectors.toMap(Function.identity(), this::getPriority));
    }
}
