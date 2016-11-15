package fr.unice.polytech.si3.qgl.iaad.init;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */
enum ArgContext {
    heading("heading"),
    men("men"),
    budget("budget"),
    amount("amount"),
    resource("resource");

    private String name;

    public String getName() { return name; }

    ArgContext(String name) { this.name = name; }
}