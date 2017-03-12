package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;

import java.awt.*;

/**
 * @Author Theo Cholley
 */

public class Crew{

    private int people;
    private Point location;

    public Crew(int people, Point initialLocation){
        this.people=people;
        this.location=initialLocation;
    }


    public int getPeople() {
        return this.people;
    }


    public Point getLocation() {
        return new Point(this.location);
    }



    public void move(Compass direction) {
        if (direction.equals(Compass.E))
            this.location = new Point((int) this.location.getX() + 1, (int) this.location.getY());
        else if (direction.equals(Compass.S))
            this.location = new Point((int) this.location.getX(), (int) this.location.getY() + 1);
        else if (direction.equals(Compass.W))
            this.location = new Point((int) this.location.getX() - 1, (int) this.location.getY());
        else if (direction.equals(Compass.N))
            this.location = new Point((int) this.location.getX(), (int) this.location.getY() - 1);
    }
}
