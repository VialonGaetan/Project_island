package result;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import fr.unice.polytech.si3.qgl.iaad.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 *         Created the 16/12/2016.
 */
public class GroundResultTest {

    private Ground result;

    @Test
    public void moveToTest()
    {
        result = new Move_to(Direction.E);
        result.putResults("{ \"cost\": 6, \"extras\": { }, \"status\": \"OK\" }\n");
        int cost=6;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getResource(3),null);
        assertEquals(result.nbResources(),-1);
        assertEquals(result.getKind(),null);
        assertEquals(result.getProduction(),-1);
        assertEquals(result.getAmountExploit(),-1);
        assertEquals(result.getAltitude(),-1);
    }

    @Test
    public void scoutTest()
    {
        result = new Scout(Direction.E);
        result.putResults("{ \"cost\": 5, \"extras\": { \"altitude\": 1, \"resources\": [\"FUR\", \"WOOD\"] }, \"status\": \"OK\" }");
        int cost = 5;
        int altitude = 1;
        int nbResources=2;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getResource(0),"FUR");
        assertEquals(result.getResource(1),"WOOD");
        assertEquals(result.nbResources(),nbResources);
        assertEquals(result.getKind(),null);
        assertEquals(result.getProduction(),-1);
        assertEquals(result.getAmountExploit(),-1);
        assertEquals(result.getAltitude(),altitude);
    }

    @Test
    public void glimpseTest()
    {
        result = new Glimpse(Direction.E,1);
        result.putResults("{ \n" +
                "  \"cost\": 3,\n" +
                "  \"extras\": {\n" +
                "    \"asked_range\": 4,\n" +
                "    \"report\": [\n" +
                "      [ [ \"BEACH\", 59.35 ], [ \"OCEAN\", 40.65 ] ],\n" +
                "      [ [ \"OCEAN\", 70.2  ], [ \"BEACH\", 29.8  ] ],\n" +
                "      [ \"OCEAN\", \"BEACH\" ],\n" +
                "      [ \"OCEAN\" ]\n" +
                "    ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "} ");
        int cost = 3;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getResource(0),null);
        assertEquals(result.getResource(1),null);
        assertEquals(result.nbResources(),-1);
        assertEquals(result.getKind(),null);
        assertEquals(result.getProduction(),-1);
        assertEquals(result.getAmountExploit(),-1);
        assertEquals(result.getAltitude(),-1);
    }

    @Test
    public void exploreTest()
    {
        result = new Explore();
        result.putResults("{\n" +
                "  \"cost\": 5,\n" +
                "  \"extras\": {\n" +
                "    \"resources\": [\n" +
                "      { \"amount\": \"HIGH\", \"resource\": \"FUR\", \"cond\": \"FAIR\" },\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"WOOD\", \"cond\": \"HARSH\" }\n" +
                "    ],\n" +
                "    \"pois\": [ \"creek-id\" ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}");
        int cost = 5;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getResource(0),null);
        assertEquals(result.getResource(1),null);
        assertEquals(result.nbResources(),-1);
        assertEquals(result.getKind(),null);
        assertEquals(result.getProduction(),-1);
        assertEquals(result.getAmountExploit(),-1);
        assertEquals(result.getAltitude(),-1);
        assertEquals(result.nbResourceExplore(),2);
    }

    @Test
    public void exploitTest()
    {
        result = new Exploit(Resource.WOOD);
        result.putResults("{ \"cost\": 3, \"extras\": {\"amount\": 9}, \"status\": \"OK\" }");
        int cost = 3;
        int amount = 9;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getResource(0),null);
        assertEquals(result.getResource(1),null);
        assertEquals(result.nbResources(),-1);
        assertEquals(result.getKind(),null);
        assertEquals(result.getProduction(),-1);
        assertEquals(result.getAmountExploit(),amount);
        assertEquals(result.getAltitude(),-1);
    }

    @Test
    public void transformTest()
    {
        result =new Transform(Resource.WOOD, Resource.QUARTZ,6,11);
        result.putResults("{ \"cost\": 5, \"extras\": { \"production\": 1, \"kind\": \"GLASS\" },\"status\": \"OK\" }");
        int cost = 5;
        int prod = 1;
        String kind = "GLASS";
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getResource(0),null);
        assertEquals(result.getResource(1),null);
        assertEquals(result.nbResources(),-1);
        assertEquals(result.getKind(),kind);
        assertEquals(result.getProduction(),prod);
        assertEquals(result.getAmountExploit(),-1);
        assertEquals(result.getAltitude(),-1);

    }


    @Ignore
    public void testGlimpseResult(){
        result = new Glimpse(Direction.S,4);
        result.putResults("{ \n" +
                "  \"cost\": 3,\n" +
                "  \"extras\": {\n" +
                "    \"asked_range\": 4,\n" +
                "    \"report\": [\n" +
                "      [ [ \"BEACH\", 59.35 ], [ \"OCEAN\", 40.65 ] ],\n" +
                "      [ [ \"OCEAN\", 70.2  ], [ \"BEACH\", 29.8  ] ],\n" +
                "      [ \"OCEAN\", \"BEACH\" ],\n" +
                "      [ \"OCEAN\" ]\n" +
                "    ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}");
        int cost = 3;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),4);
        assertEquals(result.nbReport(),4);
        assertEquals(result.getResourceReport(4),"OCEAN");

    }
}
