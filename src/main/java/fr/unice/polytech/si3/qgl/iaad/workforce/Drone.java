package fr.unice.polytech.si3.qgl.iaad.workforce;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Drone
{
    Point getLocation();

    Direction getHeading();

    void fly();

    void heading(Direction direction);
}
