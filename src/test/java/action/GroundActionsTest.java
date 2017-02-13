package action;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
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
    public void testExplore(){
        action = new Explore();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"explore\"}");
        assertEquals(toJSON,new Explore().toJSON());
        assertNotEquals(action,new Echo(direction));
    }

    @Test
    public void testExploit() {
        action = new Exploit(Resource.FUR);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"exploit\",\"parameters\":{\"resource\":\"FUR\"}}");
        assertNotEquals(toJSON, "{\"action\":\"exploit\",\"parameters\": {\"resource\":\"FRUITS\"}}");
        assertNotEquals(toJSON, new Exploit(Resource.FISH).toJSON());
        assertNotEquals(action, new Scout(Direction.S));
        assertNotEquals(toJSON, new Move_to(Direction.W).toJSON());
        assertNotEquals(toJSON, new Echo(Direction.W).toJSON());
    }


    @Test
    public void testGlimpse() {
        action = new Glimpse(Direction.S,4);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"glimpse\",\"parameters\":{\"range\":4,\"direction\":\"S\"}}");
        assertNotEquals(toJSON, "{\"action\":\"exploit\",\"parameters\": {\"resource\":\"FRUITS\"}}");
        assertNotEquals(action, new Scout(Direction.S));
        assertNotEquals(toJSON, new Move_to(Direction.W).toJSON());
        assertNotEquals(toJSON, new Echo(Direction.W).toJSON());
    }

    @Test
    public void testTransform() {
        action = new Transform(Resource.FUR,Resource.GLASS,10,6);
        toJSON = action.toJSON();
        assertNotEquals(toJSON,"{\"action\":\"transform\",\"parameters\":{\"FUR\":10,\"GLASS\":6}}");
        assertNotEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"FUR\":6,\"GLASS\":10}}");
        assertEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"GLASS\":6,\"FUR\":10}}");
        assertNotEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"GLASS\":10,\"FUR\":6}}");
        assertNotEquals(toJSON, new Transform(Resource.GLASS,Resource.FUR,10,6));
        assertNotEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"WOOD\":6,\"QUARTZ\":11}}");
        assertNotEquals(action, new Scout(Direction.S));
        assertNotEquals(toJSON, new Move_to(Direction.W).toJSON());
        assertNotEquals(toJSON, new Echo(Direction.W).toJSON());
    }

    @Test
    public void testStop(){
        action = new StopGround();
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"stop\"}");
        assertEquals(toJSON,new Stop().toJSON());
        assertNotEquals(action,new Heading(direction));
    }

    @Test
    public void testLand(){
        action = new LandGround("id",22);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"land\",\"parameters\":{\"creek\":\"id\",\"people\":22}}");
        assertEquals(toJSON,new Land("id",22).toJSON());
        assertNotEquals(toJSON,new StopGround().toJSON());
        assertNotEquals(toJSON,new Land("4532",22).toJSON());
        assertNotEquals(action,new Scout(direction));
        action = new LandGround("4532",1);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"action\":\"land\",\"parameters\":{\"creek\":\"4532\",\"people\":1}}");
        assertEquals(toJSON,new Land("4532",1).toJSON());
        assertNotEquals(toJSON,new Land("4532",22).toJSON());
        assertNotEquals(action,new Move_to(direction));
    }
}
