package fr.unice.polytech.qgl.iaad;

/**
 * @author Alexandre Clement
 *         Created the 12/11/2016.
 */
public enum Action {
    FLY("fly"),
    ECHO("echo"),
    HEADING("heading"),
    SCAN("scan"),
    STOP("stop");

    private String name;

    public String getName() {
            return name;
        }

    Action(String name) {
            this.name = name;
        }
}
