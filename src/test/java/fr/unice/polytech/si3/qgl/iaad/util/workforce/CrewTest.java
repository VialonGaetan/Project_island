package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

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

    /**
     * Check if the crew actualise well its position when they move in a given direction
     */
    @Test
    public void moveTest(){
        crew.move(Compass.E);
        assertTrue(((int)this.crew.getLocation().getX() == 1)&&((int)this.crew.getLocation().getY()==0));
        crew.move(Compass.N);
        assertTrue(((int)this.crew.getLocation().getX() == 1)&&((int)this.crew.getLocation().getY()==-1));
        crew.move(Compass.W);
        assertTrue(((int)this.crew.getLocation().getX() == 0)&&((int)this.crew.getLocation().getY()==-1));
        crew.move(Compass.S);
        assertTrue(((int)this.crew.getLocation().getX() == 0)&&((int)this.crew.getLocation().getY()==0));
        for (int i=1; i<10; i++){
            crew.move(Compass.S);
            assertTrue(((int)this.crew.getLocation().getX() == 0)&&((int)this.crew.getLocation().getY()==i));
        }
    }

}