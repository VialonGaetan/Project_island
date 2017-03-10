package fr.unice.polytech.si3.qgl.iaad.result;

import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceAmount;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceCondition;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 16/12/2016.
 */
public class GroundResultTest {


    @Test
    public void moveToTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 6, \"extras\": { }, \"status\": \"OK\" }\n"));
        MoveToResultat result = new MoveToResultat(jsonResult);
        int cost=6;
        assertEquals(result.getCost(),cost);
    }

    @Test
    public void scoutTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 5, \"extras\": { \"altitude\": 1, \"resources\": [\"FUR\", \"WOOD\"] }, \"status\": \"OK\" }"));
        ScoutResultat result= new ScoutResultat(jsonResult);
        int cost = 5;
        int nbResources=2;
        assertEquals(result.getCost(),cost);
        //todo MANQUE DES INFORMATIONS
        /*
        assertEquals(result.,"FUR");
        assertEquals(result.getResource(1),"WOOD");
        assertEquals(result.nbResources(),nbResources);
        assertEquals(result.getKind(),null);
        assertEquals(result.getProduction(),-1);
        assertEquals(result.getAmountExploit(),-1);
        assertEquals(result.getAltitude(),altitude);
        */
    }

    @Test
    public void glimpseTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \n" +
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
                "} "));
        GlimpseResultat result = new GlimpseResultat(jsonResult);
        int cost = 3;
        assertEquals(result.getCost(),cost);
    }

    @Test
    public void exploreTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{\n" +
                "  \"cost\": 5,\n" +
                "  \"extras\": {\n" +
                "    \"resources\": [\n" +
                "      { \"amount\": \"HIGH\", \"resource\": \"FUR\", \"cond\": \"FAIR\" },\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"WOOD\", \"cond\": \"HARSH\" }\n" +
                "    ],\n" +
                "    \"pois\": [ \"creek-id\" ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}"));
        ExploreResultat result= new ExploreResultat(jsonResult);
        int cost = 5;
        assertEquals(result.getCost(),cost);
        assertEquals(result.getResourcesExplored().get(0).getResourceAmount(), ResourceAmount.HIGH);
        assertEquals(result.getResourcesExplored().get(1).getResourceAmount(),ResourceAmount.LOW);
        assertEquals(result.getResourcesExplored().get(0).getResource(),Resource.FUR);
        assertEquals(result.getResourcesExplored().get(0).getResourceCondition(),ResourceCondition.FAIR);
        assertEquals(result.getResourcesExplored().get(1).getResourceCondition(), ResourceCondition.HARSH);
        assertEquals(result.getResourcesExplored().get(0).getResource(),Resource.FUR);
        assertEquals(result.getResourcesExplored().get(1).getResource(),Resource.WOOD);

    }

    @Test
    public void exploitTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 3, \"extras\": {\"amount\": 9}, \"status\": \"OK\" }"));
        ExploitResultat result = new ExploitResultat(jsonResult);

        int cost = 3;
        int amount = 9;
        assertEquals(result.getCost(),cost);
        assertEquals(result.getExploitAmount(),amount);

    }

    @Test
    public void transformTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 5, \"extras\": { \"production\": 1, \"kind\": \"GLASS\" },\"status\": \"OK\" }"));
        TransformResultat result = new TransformResultat(jsonResult);
        int cost = 5;
        int prod = 1;
        assertEquals(result.getCost(),cost);
        assertEquals(result.getTransformProduction(),prod);

    }


    @Test
    public void testGlimpseResult(){

        JsonResult jsonResult = new JsonResult(new JSONObject("{ \n" +
                "  \"cost\": 3,\n" +
                "  \"extras\": {\n" +
                "    \"asked_range\": 4,\n" +
                "    \"report\": [\n" +
                "      [ [ \"OCEAN\", 59.35 ], [ \"OCEAN\", 40.65 ], [ \"TEMPERATE_DESERT\", 40.65 ] ],\n" +
                "      [ [ \"OCEAN\", 70.2  ], [ \"SNOW\", 29.8  ] ],\n" +
                "      [ \"OCEAN\", \"BEACH\" ],\n" +
                "      [ \"OCEAN\" ]\n" +
                "    ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}"));
        GlimpseResultat result = new GlimpseResultat(jsonResult);
        int cost = 3;
    }
}
