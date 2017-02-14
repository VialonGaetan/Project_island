package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author Theo Cholley
 * @since 13/02/2017.
 */
public interface ICrew
{
    int getPeople();

    Point getLocation();

    void move(Direction direction);
}
