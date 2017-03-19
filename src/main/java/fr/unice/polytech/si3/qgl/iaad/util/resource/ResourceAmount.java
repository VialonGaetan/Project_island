package fr.unice.polytech.si3.qgl.iaad.util.resource;


public enum ResourceAmount
{
    HIGH(1),
    MEDIUM(0.67),
    LOW(0.33);

    private final double quantity;

    ResourceAmount(double quantity)
    {
        this.quantity = quantity;
    }

    public double getQuantity()
    {
        return quantity;
    }
}
