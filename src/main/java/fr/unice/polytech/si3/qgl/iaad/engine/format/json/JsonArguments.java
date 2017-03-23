package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */
public enum JsonArguments
{
    HEADING("heading"),
    MEN("men"),
    BUDGET("budget"),
    AMOUNT("amount"),
    RESOURCE("resource"),
    RESOURCES("resources"),
    CONTRACTS("contracts"),
    COST("cost"),
    EXTRAS("extras"),
    STATUS("status"),
    PARAMETERS("parameters"),
    COND("cond"),
    RANGE("range"),
    FOUND("found"),
    BIOMES("biomes"),
    CREEKS("creeks"),
    SITES("sites"),
    ACTION("action"),
    DIRECTION("direction"),
    CREEK("creek"),
    PEOPLE("people"),
    PRODUCTION("production");

    private String name;

    JsonArguments(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
