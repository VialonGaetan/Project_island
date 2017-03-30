package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

/**
 * save all the json arguments
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

    /**
     * the string associated to an argument
     */
    private String name;

    /**
     * init the string
     * @param name, String value
     */
    JsonArguments(String name)
    {
        this.name = name;
    }

    /**
     * return the string associated to the argument
     * @return a String value
     */
    @Override
    public String toString()
    {
        return name;
    }
}
