package action;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 *         Created the 16/12/2016.
 */
public class GroundActionsTest {

    private Action action;
    private String toJSON;
    private Direction direction;

    @Before
    public void defineContext() {
        direction = Direction.W;
    }

    @Test
    public void testScout(){
        action = new Scout(direction);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"scout\",\"parameters\":{\"direction\":\"W\"}}");
        assertEquals(action.toJSON(), new Scout(Direction.W).toJSON());
        assertNotEquals(action,new Heading(direction));
        assertNotEquals(action,new Scout(Direction.S));
    }

    @Test
    public void testMoveTo(){
        action = new Move_to(direction);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"move_to\",\"parameters\":{\"direction\":\"W\"}}");
        assertNotEquals(toJSON,"{\"action\":\"scout\",\"parameters\":{\"direction\":\"N\"}}");
        assertNotEquals(action,new Scout(Direction.S));
        assertEquals(toJSON,new Move_to(Direction.W).toJSON());
        assertNotEquals(toJSON,new Echo(Direction.W).toJSON());
    }


    @Test
    public void testScan(){
        action = new Explore();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"explore\"}");
        assertEquals(toJSON,new Explore().toJSON());
        assertNotEquals(action,new Echo(direction));
    }

    @Test
    public void testStop(){
        action = new StopGround();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"stop\"}");
        assertEquals(toJSON,new Stop().toJSON());
        assertNotEquals(action,new Heading(direction));
    }
}
