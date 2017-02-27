package fr.unice.polytech.si3.qgl.iaad.workforce;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.workforce.Crew;
import org.junit.Before;
import org.junit.Test;


import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Théo on 13/02/2017.
 */
public class CrewTest {

    Crew crew;
    Point location;

    @Before
    public void init(){
        this.location = new Point(0,0);
        crew= new Crew(50,location);
    }

    @Test
    public void moveTest(){
        crew.move(Direction.E);
        assertTrue(((int)this.crew.getLocation().getX() == 1)&&((int)this.crew.getLocation().getY()==0));
        crew.move(Direction.N);
        assertTrue(((int)this.crew.getLocation().getX() == 1)&&((int)this.crew.getLocation().getY()==-1));
        crew.move(Direction.W);
        assertTrue(((int)this.crew.getLocation().getX() == 0)&&((int)this.crew.getLocation().getY()==-1));
        crew.move(Direction.S);
        assertTrue(((int)this.crew.getLocation().getX() == 0)&&((int)this.crew.getLocation().getY()==0));
        for (int i=1; i<10; i++){
            crew.move(Direction.S);
            assertTrue(((int)this.crew.getLocation().getX() == 0)&&((int)this.crew.getLocation().getY()==i));
        }
    }

}