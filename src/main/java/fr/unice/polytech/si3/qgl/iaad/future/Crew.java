package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Crew
{
    int getPeople();

    Point getLocation();

    void move(Direction direction);
}
