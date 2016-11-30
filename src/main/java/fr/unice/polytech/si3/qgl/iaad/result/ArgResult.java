package fr.unice.polytech.si3.qgl.iaad.result;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public enum ArgResult {
    COST("cost"),
    STATUS("status"),
    EXTRAS("extras"),
    RANGE("range"),
    BIOMES("biomes"),
    CREEKS("creeks"),
    SITES("sites"),
    FOUND("found"),
    ALTITUDE("altitude"),
    RESOURCES("resources");

    private String name;

    public String getName() {
        return name;
    }

    ArgResult(String name) {
        this.name = name;
    }
}
