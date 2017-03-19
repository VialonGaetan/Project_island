package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.resource.*;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
class Prioritize
{
    private final ModeledMap map;
    private final Contract contract;

    Prioritize(ModeledMap map, Contract contract)
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
        return getDifficulty(primaryResource) / contract.getOrDefault(fr.unice.polytech.si3.qgl.iaad.util.resource.Resource.valueOf(primaryResource.toString()), -1);
    }

    double getPriority(Manufactured manufactured)
    {
        return manufactured.getRecipe().keySet().stream().mapToDouble(this::getDifficulty).min().orElse(0) / manufactured.getFactor() / contract.getOrDefault(fr.unice.polytech.si3.qgl.iaad.util.resource.Resource.valueOf(manufactured.toString()), -1);
    }
}
