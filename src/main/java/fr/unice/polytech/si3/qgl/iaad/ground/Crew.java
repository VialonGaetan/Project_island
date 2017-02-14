package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.future.ICrew;

import java.awt.*;

/**
 * @Author Theo Cholley
 */

public class Crew implements ICrew{

    private int people;
    private Point location;

    public Crew(int people, Point initialLocation){
        this.people=people;
        this.location=initialLocation;
    }

    @Override
    public int getPeople() {
        return this.people;
    }

    @Override
    public Point getLocation() {
        return this.location;
    }

    @Override
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
