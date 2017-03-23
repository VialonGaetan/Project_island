package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public abstract class ResourceContract<T extends Resource>
{
    private final T resource;
    private final int total;
    private int collected;

    public ResourceContract(T resource, int total)
    {
        this.resource = resource;
        this.total = total;
        collected = 0;
    }

    public T getResource()
    {
        return resource;
    }

    public int getTotalQuantity()
    {
        return total;
    }

    public int getCollectedQuantity()
    {
        return collected;
    }

    public int getRemainingQuantity()
    {
        return Math.max(0, getTotalQuantity() - getCollectedQuantity());
    }

    public abstract Basket<PrimaryResource> getNecessaryPrimaryResource();

    public void collect(int quantity)
    {
        collected += quantity;
    }

    public boolean isComplete()
    {
        return getRemainingQuantity() == 0;
    }

    public boolean notComplete()
    {
        return !isComplete();
    }
}
