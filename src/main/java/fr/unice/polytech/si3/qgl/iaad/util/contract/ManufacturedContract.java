package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public class ManufacturedContract extends ResourceContract<Manufactured>
{
    public ManufacturedContract(Manufactured manufactured, int quantity)
    {
        super(manufactured, quantity);
    }

    public Set<PrimaryContract> getPrimaryContracts()
    {
        Set<PrimaryContract> primaryContract = new HashSet<>();
        for (Map.Entry<PrimaryResource, Double> entry : getResource().getRecipe().entrySet())
            primaryContract.add(new PrimaryContract(entry.getKey(), (int) Math.ceil(getRemainingQuantity() * entry.getValue())));
        return primaryContract;
    }

    @Override
    public Basket<PrimaryResource> getNecessaryPrimaryResource()
    {
        Basket<PrimaryResource> basket = new Basket<>(PrimaryResource.class);
        for (PrimaryContract primaryContract : getPrimaryContracts())
        {
            basket.add(primaryContract.getResource(), primaryContract.getRemainingQuantity());
        }
        return basket;
    }
}
