import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    }

    @Test
    public void testEcho(){
        action = new Echo(direction);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"echo\",\"parameters\":{\"direction\":\"S\"}}");
        assertNotEquals(toJSON,"{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}");
    }

    @Test
    public void testHeading(){
        action = new Heading(direction);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"heading\",\"parameters\":{\"direction\":\"S\"}}");
        assertNotEquals(toJSON,"{\"action\":\"heading\",\"parameters\":{\"direction\":\"N\"}}");
    }

    @Test
    public void testScan(){
        action = new Scan();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"scan\"}");
    }
}
