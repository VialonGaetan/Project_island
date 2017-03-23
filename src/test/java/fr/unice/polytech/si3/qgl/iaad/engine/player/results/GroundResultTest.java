package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceAmount;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceCondition;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Gaetan Vialon
 *         Created the 16/12/2016.
 */
public class GroundResultTest {


    @Test
    public void moveToTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 6, \"extras\": { }, \"status\": \"OK\" }\n"));
        MoveToResult result = new MoveToResult(jsonResult);
        int cost=6;
        assertEquals(result.getCost(),cost);
    }

    @Test
    public void scoutTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 5, \"extras\": { \"altitude\": 1, \"resources\": [\"FUR\", \"WOOD\"] }, \"status\": \"OK\" }"));
        ScoutResult result= new ScoutResult(jsonResult);
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
        GlimpseResult result = new GlimpseResult(jsonResult);
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
        ExploreResult result= new ExploreResult(jsonResult);
        int cost = 5;
        assertEquals(result.getCost(),cost);
        assertEquals(result.getResourcesExplored().get(0).getResourceAmount(), ResourceAmount.HIGH);
        assertEquals(result.getResourcesExplored().get(1).getResourceAmount(),ResourceAmount.LOW);
        assertEquals(result.getResourcesExplored().get(0).getResource(),PrimaryResource.FUR);
        assertEquals(result.getResourcesExplored().get(0).getResourceCondition(),ResourceCondition.FAIR);
        assertEquals(result.getResourcesExplored().get(1).getResourceCondition(), ResourceCondition.HARSH);
        assertEquals(result.getResourcesExplored().get(0).getResource(),PrimaryResource.FUR);
        assertEquals(result.getResourcesExplored().get(1).getResource(), PrimaryResource.WOOD);

    }

    @Test
    public void exploitTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 3, \"extras\": {\"amount\": 9}, \"status\": \"OK\" }"));
        ExploitResult result = new ExploitResult(jsonResult);

        int cost = 3;
        int amount = 9;
        assertEquals(result.getCost(),cost);
        assertEquals(result.getExploitAmount(),amount);

    }

    @Test
    public void transformTest()
    {
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 5, \"extras\": { \"production\": 1, \"kind\": \"GLASS\" },\"status\": \"OK\" }"));
        TransformResult result = new TransformResult(jsonResult);
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
                "      [ [ \"BEACH\", 59.35 ], [ \"OCEAN\", 40.65 ] ],\n" +
                "      [ [ \"OCEAN\", 70.2  ], [ \"BEACH\", 29.8  ] ],\n" +
                "      [ \"OCEAN\", \"BEACH\" ],\n" +
                "      [ \"OCEAN\" ]\n" +
                "    ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "} "));
        GlimpseResult glimpseResult = new GlimpseResult(jsonResult);
        assertEquals(glimpseResult.getGlimpseInformation().size(), 7);
        assertEquals(glimpseResult.getGlimpseInformation().get(0).getBiome(), Biome.BEACH);
        assertEquals(glimpseResult.getGlimpseInformation().get(1).getBiome(), Biome.OCEAN);
        assertEquals(glimpseResult.getGlimpseInformation().get(2).getBiome(), Biome.OCEAN);
        assertEquals(glimpseResult.getGlimpseInformation().get(3).getBiome(), Biome.BEACH);
        assertEquals(glimpseResult.getGlimpseInformation().get(4).getBiome(), Biome.OCEAN);
        assertEquals(glimpseResult.getGlimpseInformation().get(5).getBiome(), Biome.BEACH);
        assertEquals(glimpseResult.getGlimpseInformation().get(6).getBiome(), Biome.OCEAN);
        assertEquals(glimpseResult.getGlimpseInformation().get(0).getRange(), 1);
        assertEquals(glimpseResult.getGlimpseInformation().get(1).getRange(), 1);
        assertEquals(glimpseResult.getGlimpseInformation().get(2).getRange(), 2);
        assertEquals(glimpseResult.getGlimpseInformation().get(3).getRange(), 2);
        assertEquals(glimpseResult.getGlimpseInformation().get(4).getRange(), 3);
        assertEquals(glimpseResult.getGlimpseInformation().get(5).getRange(), 3);
        assertEquals(glimpseResult.getGlimpseInformation().get(6).getRange(), 4);
        assertTrue(glimpseResult.getGlimpseInformation().get(0).getPercentage() == 59.35);
        assertTrue(glimpseResult.getGlimpseInformation().get(1).getPercentage() == 40.65);
        assertTrue(glimpseResult.getGlimpseInformation().get(2).getPercentage() == 70.2);
        assertTrue(glimpseResult.getGlimpseInformation().get(3).getPercentage() == 29.8);

    }
}
