package fr.unice.polytech.si3.qgl.iaad.engine.format;

/**
 * manage the budget
 * @author Alexandre Clement
 * @since 26/03/2017.
 */
public class Budget
{
    /**
     * below 200, the budget is considered as low
     */
    private static final int LOW_BUDGET = 200;

    /**
     * value of the budget
     */
    private int value;

    /**
     * init the budget
     * @param budget, int value
     */
    public Budget(int budget)
    {
        this.value = budget;
    }

    public int getValue()
    {
        return value;
    }

    /**
     * check is the budget is low
     * @return true if budget is under 200, false otherwise
     */
    public boolean isLow()
    {
        return value < LOW_BUDGET;
    }

    /**
     * update budget when there is a cost
     * @param cost, int value
     */
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

    /**
     * check if the budget is higher than the cost with considering the budget must be higher than 200
     * @param cost, double value
     * @return true if the budget is higher than the cost + 200
     */
    public boolean isGreaterThan(double cost)
    {
        return value > cost + LOW_BUDGET;
    }
}
