package fr.unice.polytech.si3.qgl.iaad.engine.format;

/**
 * @author Alexandre Clement
 * @since 26/03/2017.
 */
public class Budget
{
    private static final int LOW_BUDGET = 200;
    private int value;

    public Budget(int budget)
    {
        this.value = budget;
    }

    public int getValue()
    {
        return value;
    }

    public boolean isLow()
    {
        return value < LOW_BUDGET;
    }

    public void deduce(int cost)
    {
        value -= cost;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Budget budget1 = (Budget) o;

        return value == budget1.value;
    }

    @Override
    public int hashCode()
    {
        return value;
    }

    public boolean isGreaterThan(double cost)
    {
        return value > cost + LOW_BUDGET;
    }
}
