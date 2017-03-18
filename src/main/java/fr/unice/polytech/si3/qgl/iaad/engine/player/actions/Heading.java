package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Heading extends Oriented
{
    public Heading(Compass direction) {
        super(ArgActions.HEADING, direction);
    }
}
