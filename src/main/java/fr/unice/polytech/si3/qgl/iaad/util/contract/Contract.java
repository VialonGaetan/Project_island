package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public class Contract
{
    private final Map<PrimaryResource, PrimaryContract> primaryResourceMap;
    private final Map<Manufactured, ManufacturedContract> manufacturedResourceMap;

    public Contract()
    {
        primaryResourceMap = new EnumMap<>(PrimaryResource.class);
        manufacturedResourceMap = new EnumMap<>(Manufactured.class);
    }

    public void addContract(PrimaryContract contract)
    {
        primaryResourceMap.put(contract.getResource(), contract);
    }

    public void addContract(ManufacturedContract contract)
    {
        manufacturedResourceMap.put(contract.getResource(), contract);
    }

    public PrimaryContract get(PrimaryResource resource)
    {
        return primaryResourceMap.getOrDefault(resource, new PrimaryContract(resource, 0));
    }

    public Collection<PrimaryContract> getPrimaryContracts()
    {
        return primaryResourceMap.values();
    }

    public Collection<ManufacturedContract> getManufacturedContracts()
    {
        return manufacturedResourceMap.values();
    }

    public ManufacturedContract get(Manufactured resource)
    {
        return manufacturedResourceMap.getOrDefault(resource, new ManufacturedContract(resource, 0));
    }

    public boolean allCompleted()
    {
        return primaryResourceMap.values().stream().allMatch(ResourceContract::isComplete) && manufacturedResourceMap.values().stream().allMatch(ResourceContract::isComplete);
    }

    public Basket<PrimaryResource> getTotalBasket()
    {
        Basket<PrimaryResource> basket = new Basket<>(PrimaryResource.class);
        for (PrimaryContract primaryContract : primaryResourceMap.values())
        {
            basket.add(primaryContract.getNecessaryPrimaryResource());
        }
        for (ManufacturedContract manufacturedContract : manufacturedResourceMap.values())
        {
            basket.add(manufacturedContract.getNecessaryPrimaryResource());
        }
        return basket;
    }

}
