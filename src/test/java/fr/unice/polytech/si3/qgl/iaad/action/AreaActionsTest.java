package fr.unice.polytech.si3.qgl.iaad.action;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 * Created the 19/11/2016.
 */

public class AreaActionsTest {

    private Decision action;
    private String toJSON;
    private Direction direction;

    @Before
    public void defineContext() {
        direction = Direction.S;
    }

    @Test
    public void testFly(){
        action = new Fly();
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"fly\"}");
        assertEquals(action.getJsonObject().toString(), new Fly().getJsonObject().toString());
        assertNotEquals(action,new Heading(Direction.W));
    }

    @Test
    public void testEcho(){
        action = new Echo(direction);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"echo\",\"parameters\":{\"direction\":\"S\"}}");
        assertNotEquals(toJSON,"{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}");
        assertNotEquals(action,new Fly());
        assertEquals(toJSON,new Echo(Direction.S).getJsonObject().toString());
        assertNotEquals(toJSON,new Echo(Direction.W).getJsonObject().toString());
    }

    @Test
    public void testHeading(){
        action = new Heading(direction);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"heading\",\"parameters\":{\"direction\":\"S\"}}");
        assertNotEquals(toJSON,"{\"action\":\"heading\",\"parameters\":{\"direction\":\"N\"}}");
        assertEquals(toJSON,new Heading(Direction.S).getJsonObject().toString());
        assertNotEquals(toJSON,new Heading(Direction.W).getJsonObject().toString());
        assertNotEquals(action,new Scan());
    }

    @Test
    public void testScan(){
        action = new Scan();
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"scan\"}");
        assertEquals(toJSON,new Scan().getJsonObject().toString());
        assertNotEquals(action,new Echo(direction));
    }

    @Test
    public void testStop(){
        action = new Stop();
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"stop\"}");
        assertEquals(toJSON,new Stop().getJsonObject().toString());
        assertNotEquals(action,new Heading(direction));
    }

    @Test
    public void testLand(){
        action = new Land("id",22);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"land\",\"parameters\":{\"creek\":\"id\",\"people\":22}}");
        assertEquals(toJSON,new Land("id",22).getJsonObject().toString());
        assertNotEquals(toJSON,new Stop().getJsonObject().toString());
        assertNotEquals(toJSON,new Land("4532",22).getJsonObject().toString());
        assertNotEquals(action,new Heading(direction));
        action = new Land("4532",1);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"land\",\"parameters\":{\"creek\":\"4532\",\"people\":1}}");
        assertEquals(toJSON,new Land("4532",1).getJsonObject().toString());
        assertNotEquals(toJSON,new Land("4532",22).getJsonObject().toString());
        assertNotEquals(action,new Heading(direction));
    }
}
