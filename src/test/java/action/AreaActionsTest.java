package action;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 * Created the 19/11/2016.
 */

public class AreaActionsTest {

    private Action action;
    private String toJSON;
    private Direction direction;

    @Before
    public void defineContext() {
        direction = Direction.S;
    }

    @Test
    public void testFly(){
        action = new Fly();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"fly\"}");
        assertEquals(action.toJSON(), new Fly().toJSON());
        assertNotEquals(action,new Heading(Direction.W));
    }

    @Test
    public void testEcho(){
        action = new Echo(direction);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"echo\",\"parameters\":{\"direction\":\"S\"}}");
        assertNotEquals(toJSON,"{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}");
        assertNotEquals(action,new Fly());
        assertEquals(toJSON,new Echo(Direction.S).toJSON());
        assertNotEquals(toJSON,new Echo(Direction.W).toJSON());
    }

    @Test
    public void testHeading(){
        action = new Heading(direction);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"heading\",\"parameters\":{\"direction\":\"S\"}}");
        assertNotEquals(toJSON,"{\"action\":\"heading\",\"parameters\":{\"direction\":\"N\"}}");
        assertEquals(toJSON,new Heading(Direction.S).toJSON());
        assertNotEquals(toJSON,new Heading(Direction.W).toJSON());
        assertNotEquals(action,new Scan());
    }

    @Test
    public void testScan(){
        action = new Scan();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"scan\"}");
        assertEquals(toJSON,new Scan().toJSON());
        assertNotEquals(action,new Echo(direction));
    }

    @Test
    public void testStop(){
        action = new Stop();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"stop\"}");
        assertEquals(toJSON,new Stop().toJSON());
        assertNotEquals(action,new Heading(direction));
    }

    @Test
    public void testLand(){
        action = new Land("id",22);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"land\",\"parameters\":{\"creek\":\"id\",\"people\":22}}");
        assertEquals(toJSON,new Land("id",22).toJSON());
        assertNotEquals(toJSON,new Stop().toJSON());
        assertNotEquals(toJSON,new Land("4532",22).toJSON());
        assertNotEquals(action,new Heading(direction));
        action = new Land("4532",1);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"land\",\"parameters\":{\"creek\":\"4532\",\"people\":1}}");
        assertEquals(toJSON,new Land("4532",1).toJSON());
        assertNotEquals(toJSON,new Land("4532",22).toJSON());
        assertNotEquals(action,new Heading(direction));
    }
}
