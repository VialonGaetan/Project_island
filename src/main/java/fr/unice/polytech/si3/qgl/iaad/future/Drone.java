package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Drone
{
    Point getLocation();

    void fly(Direction direction);

    void heading(Direction direction);
}
