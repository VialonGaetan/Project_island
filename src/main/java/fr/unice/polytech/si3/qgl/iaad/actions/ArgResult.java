package fr.unice.polytech.si3.qgl.iaad.actions;

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
    RESOURCES("resources"),
    RESOURCE("resource"),
    ASKED_RANGE("asked_range"),
    REPORT("report"),
    AMOUNT("amount"),
    COND("found"),
    POIS("pois"),
    PRODUCTION("production"),
    KIND("kind");

    private String name;

    public String getName() {
        return name;
    }

    ArgResult(String name) {
        this.name = name;
    }
}
