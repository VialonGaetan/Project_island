package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Contract
{
    Resource getResource();

    int getAmount();

    void collect(int amount);

    int getCollectedAmount();

    default int getRemainingAmount()
    {
        return Math.max(0, getAmount() - getCollectedAmount());
    }

    default boolean isComplete()
    {
        return getRemainingAmount() == 0;
    }
}
