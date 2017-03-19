package fr.unice.polytech.si3.qgl.iaad.util.resource;


public enum ResourceCondition
{
    HARSH(0.6),
    EASY(1.3),
    FAIR(1);

    private final double condition;

    ResourceCondition(double condition)
    {
        this.condition = condition;
    }

    public double getCondition()
    {
        return condition;
    }
}
