package fr.unice.polytech.si3.qgl.iaad.action;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 *         Created the 16/12/2016.
 */
public class GroundActionsTest {

    private Decision action;
    private String toJSON;
    private Compass direction;

    @Before
    public void defineContext() {
        direction = Compass.W;
    }

    @Test
    public void testScout(){
        action = new Scout(direction);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"scout\",\"parameters\":{\"direction\":\"W\"}}");
        assertEquals(action.getJsonObject().toString(), new Scout(Compass.W).getJsonObject().toString());
        assertNotEquals(action,new Heading(direction));
        assertNotEquals(action,new Scout(Compass.S));
    }

    @Test
    public void testMoveTo(){
        action = new Move_to(direction);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"move_to\",\"parameters\":{\"direction\":\"W\"}}");
        assertNotEquals(toJSON,"{\"action\":\"scout\",\"parameters\":{\"direction\":\"N\"}}");
        assertNotEquals(action,new Scout(Compass.S));
        assertEquals(toJSON,new Move_to(Compass.W).getJsonObject().toString());
        assertNotEquals(toJSON,new Echo(Compass.W).getJsonObject().toString());
    }


    @Test
    public void testExplore(){
        action = new Explore();
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"explore\"}");
        assertEquals(toJSON,new Explore().getJsonObject().toString());
        assertNotEquals(action,new Echo(direction));
    }

    @Test
    public void testExploit() {
        action = new Exploit(Resource.FUR);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"exploit\",\"parameters\":{\"resource\":\"FUR\"}}");
        assertNotEquals(toJSON, "{\"action\":\"exploit\",\"parameters\": {\"resource\":\"FRUITS\"}}");
        assertNotEquals(toJSON, new Exploit(Resource.FISH).getJsonObject().toString());
        assertNotEquals(action, new Scout(Compass.S));
        assertNotEquals(toJSON, new Move_to(Compass.W).getJsonObject().toString());
        assertNotEquals(toJSON, new Echo(Compass.W).getJsonObject().toString());
    }


    @Test
    public void testGlimpse() {
        action = new Glimpse(Compass.S,4);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"glimpse\",\"parameters\":{\"range\":4,\"direction\":\"S\"}}");
        assertNotEquals(toJSON, "{\"action\":\"exploit\",\"parameters\": {\"resource\":\"FRUITS\"}}");
        assertNotEquals(action, new Scout(Compass.S));
        assertNotEquals(toJSON, new Move_to(Compass.W).getJsonObject().toString());
        assertNotEquals(toJSON, new Echo(Compass.W).getJsonObject().toString());
    }

    @Test
    public void testTransform() {
        action = new Transform(Resource.FUR,Resource.GLASS,10,6);
        toJSON = action.getJsonObject().toString();
        assertNotEquals(toJSON,"{\"action\":\"transform\",\"parameters\":{\"FUR\":10,\"GLASS\":6}}");
        assertNotEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"FUR\":6,\"GLASS\":10}}");
        assertEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"GLASS\":6,\"FUR\":10}}");
        assertNotEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"GLASS\":10,\"FUR\":6}}");
        assertNotEquals(toJSON, new Transform(Resource.GLASS,Resource.FUR,10,6));
        assertNotEquals(toJSON, "{\"action\":\"transform\",\"parameters\":{\"WOOD\":6,\"QUARTZ\":11}}");
        assertNotEquals(action, new Scout(Compass.S));
        assertNotEquals(toJSON, new Move_to(Compass.W).getJsonObject().toString());
        assertNotEquals(toJSON, new Echo(Compass.W).getJsonObject().toString());
    }

}
