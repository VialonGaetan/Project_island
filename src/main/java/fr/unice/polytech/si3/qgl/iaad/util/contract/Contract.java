package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class Contract
{
    private final Resource resource;
    private final int amount;
    private int collected;

    public Contract(Resource resource, int amount)
    {
        this.resource = resource;
        this.amount = amount;
        collected = 0;
    }

    public Resource getResource()
    {
        return resource;
    }

    public int getAmount()
    {
        return amount;
    }

    public void collect(int amount)
    {
        collected += amount;
    }

    public int getCollectedAmount()
    {
        return collected;
    }

    public int getRemainingAmount()
    {
        return Math.max(0, getAmount() - getCollectedAmount());
    }

    public boolean isComplete()
    {
        return getRemainingAmount() == 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Contract that = (Contract) o;

        boolean sameAmount = amount == that.amount;
        boolean sameCollectedAmount = collected == that.collected;
        boolean sameResource = resource == that.resource;
        return sameAmount && sameCollectedAmount && sameResource;
    }

    @Override
    public int hashCode()
    {
        int result = resource.hashCode();
        result = 31 * result + amount;
        result = 31 * result + collected;
        return result;
    }
}
