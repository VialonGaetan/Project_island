package fr.unice.polytech.si3.qgl.iaad;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */

public enum Resource {
    FISH("FISH"),
    FLOWER("FLOWER"),
    FRUITS("FRUITS"),
    FUR("FUR"),
    ORE("ORE"),
    QUARTZ("QUARTZ"),
    SUGAR_CANE("SUGAR_CANE"),
    WOOD("WOOD"),
    GLASS("GLASS"),
    INGOT("INGOT"),
    LEATHER("LEATHER"),
    PLANK("PLANK"),
    RUM("RUM");

    private String name;

    public String getName() {
        return name;
    }

    Resource(String name) {
        this.name = name;
    }

}
