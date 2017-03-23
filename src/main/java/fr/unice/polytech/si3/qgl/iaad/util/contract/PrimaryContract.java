package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;

/**
 * @author Alexandre Clement
 * @since 20/03/2017.
 */
public class PrimaryContract extends ResourceContract<PrimaryResource>
{
    public PrimaryContract(PrimaryResource primaryResource, int quantity)
    {
        super(primaryResource, quantity);
    }

    @Override
    public Basket<PrimaryResource> getNecessaryPrimaryResource()
    {
        Basket<PrimaryResource> basket = new Basket<>(PrimaryResource.class);
        basket.add(getResource(), getRemainingQuantity());
        return basket;
    }
}
