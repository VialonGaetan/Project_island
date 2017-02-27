package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class StandardContract implements Contract
{
    private final Resource resource;
    private final int amount;
    private int collected;

    public StandardContract(Resource resource, int amount)
    {
        this.resource = resource;
        this.amount = amount;
        collected = 0;
    }

    @Override
    public Resource getResource()
    {
        return resource;
    }

    @Override
    public int getAmount()
    {
        return amount;
    }

    @Override
    public void collect(int amount)
    {
        collected += amount;
    }

    @Override
    public int getCollectedAmount()
    {
        return collected;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        StandardContract that = (StandardContract) o;

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
