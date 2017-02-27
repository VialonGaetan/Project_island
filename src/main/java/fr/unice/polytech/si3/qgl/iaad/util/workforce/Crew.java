package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;

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
        return this.location;
    }


    public void move(Direction direction) {
        switch (direction){

            case E: {
                this.location = new Point((int) this.location.getX()+1, (int) this.location.getY());
                break;
            }

            case S: {
                this.location = new Point((int) this.location.getX(), (int) this.location.getY()+1);
                break;
            }

            case W: {
                this.location = new Point((int) this.location.getX()-1, (int) this.location.getY());
                break;
            }

            case N:{
                this.location = new Point((int) this.location.getX(), (int) this.location.getY()-1);
                break;
            }

            default: break;
        }
    }
}
