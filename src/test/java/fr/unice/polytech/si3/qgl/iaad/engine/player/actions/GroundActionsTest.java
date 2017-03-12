package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
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
    private Compass compass;

    @Before
    public void defineContext() {
        compass = Compass.W;
    }

    @Test
    public void testScout(){
        action = new Scout(compass);
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"scout\",\"parameters\":{\"direction\":\"W\"}}");
        assertEquals(action.getJsonObject().toString(), new Scout(Compass.W).getJsonObject().toString());
        assertNotEquals(action,new Heading(compass));
        assertNotEquals(action,new Scout(Compass.S));
    }

    @Test
    public void testMoveTo(){
        action = new Move_to(compass);
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
        assertNotEquals(action,new Echo(compass));
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

    @Test
    public void testStop(){
        action = new Stop();
        toJSON = action.getJsonObject().toString();
        assertEquals(toJSON,"{\"action\":\"stop\"}");
        assertEquals(toJSON,new Stop().getJsonObject().toString());
        assertNotEquals(action,new Heading(compass));
    }

    /*
    @Test
    public void testLand(){
        action = new LandGround("id",22);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"fr.unice.polytech.si3.qgl.iaad.action\":\"land\",\"parameters\":{\"creek\":\"id\",\"people\":22}}");
        assertEquals(toJSON,new Land("id",22).toJSON());
        assertNotEquals(toJSON,new StopGround().toJSON());
        assertNotEquals(toJSON,new Land("4532",22).toJSON());
        assertNotEquals(action,new Scout(direction));
        action = new LandGround("4532",1);
        toJSON = action.toJSON();
        assertEquals(toJSON,"{\"fr.unice.polytech.si3.qgl.iaad.action\":\"land\",\"parameters\":{\"creek\":\"4532\",\"people\":1}}");
        assertEquals(toJSON,new Land("4532",1).toJSON());
        assertNotEquals(toJSON,new Land("4532",22).toJSON());
        assertNotEquals(action,new Move_to(direction));
    }
    */
}
