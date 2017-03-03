package fr.unice.polytech.si3.qgl.iaad.util.map;

import java.awt.*;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */
public enum Compass
{
    N(new Point(0, -1)),
    E(new Point(1, 0)),
    S(new Point(0, 1)),
    W(new Point(-1, 0));

    private Point vecteur;

    Compass(Point vecteur)
    {
        this.vecteur = vecteur;
    }

    public Point getVecteur()
    {
        return new Point(vecteur);
    }

    public Compass get(Direction direction)
    {
        return Compass.values()[(this.ordinal() + direction.ordinal()) % 4];
    }
}
