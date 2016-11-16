package fr.unice.polytech.si3.qgl.iaad.result;

/**
 * Created by user on 15/11/2016.
 */
public enum ArgResult {
    COST("cost"),
    STATUS("status"),
    EXTRAS("extras"),
    RANGE("range"),
    BIOMES("biomes"),
    CREEK("creek"),
    SITES("sites"),
    OUT_OF_RANGE("OUT_OF_RANGE"),
    GROUND("GROUND");

    private String name;

    public String getName() {
        return name;
    }

    ArgResult(String name) {
        this.name = name;
    }
}
