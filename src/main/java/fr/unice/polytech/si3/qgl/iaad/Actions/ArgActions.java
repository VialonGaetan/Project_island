package fr.unice.polytech.si3.qgl.iaad.Actions;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */

public enum ArgActions {
    FLY("fly"),
    HEADING("heading"),
    ECHO("echo"),
    SCAN("scan"),
    STOP("stop"),
    LAND("land"),
    MOVE_TO("move_to"),
    SCOUT("scout"),
    GLIMPSE("glimpse"),
    EXPLORE("explore"),
    EXPLOIT("exploit"),
    TRANSFORM("transform");

    private String name;

    public String getName() {
        return name;
    }

    ArgActions(String name) {
        this.name = name;
    }
}
