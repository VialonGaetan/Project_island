package fr.unice.polytech.si3.qgl.iaad.result;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 * Created the 20/11/2016.
 */

public class AreaResultTest {

    private Area result;

    @Before
    public void defineContext() {}

    @Test
    public void testFlyResult(){
        result = new Fly();
        result.putResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }\n");
        int cost = 2;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
        assertEquals(result.getBiome(0),null);
        assertEquals(result.nbBiomes(),-1);
        assertEquals(result.nbCreeks(),-1);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),null);

    }

    @Test
    public void testEchoResult(){
        result = new Echo(Direction.W);
        result.putResults("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        int cost1 = 1, range1 = 0;
        String status = "OK",found = "OUT_OF_RANGE";
        assertEquals(result.getCost(),cost1);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),range1);
        assertEquals(result.getFound(),found);
        assertEquals(result.getBiome(0),null);
        assertEquals(result.nbBiomes(),-1);
        assertEquals(result.nbCreeks(),-1);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),null);
        //fr.unice.polytech.si3.qgl.iaad.result = new EchoResult("{ \"cost\": 10, \"extras\": { \"range\": 55, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        result.putResults("{ \"cost\": 10, \"extras\": { \"range\": 55, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        int cost2 = 10;
        int range2 = 55;
        status = "OK";
        found = "GROUND";
        assertEquals(result.getCost(),cost2);
        assertNotEquals(result.getCost(), cost1);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),range2);
        assertNotEquals(result.getFound(), range1);
        assertEquals(result.getFound(),found);
        assertEquals(result.getBiome(0),null);
        assertEquals(result.nbBiomes(),-1);
        assertEquals(result.nbCreeks(),-1);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),null);

    }

    @Test
    public void testHeadingResult(){
        result = new Heading(Direction.E);
        result.putResults("{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }\n");
        int cost = 4;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
        assertEquals(result.getBiome(0),null);
        assertEquals(result.nbBiomes(),-1);
        assertEquals(result.nbCreeks(),-1);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),null);
    }

    @Test
    public void testScanResult(){
        result = new Scan();
        result.putResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"idsite\"]}, \"status\": \"OK\"}");
        int cost = 2;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
        assertEquals(result.getBiome(0),"BEACH");
        assertEquals(result.nbBiomes(),1);
        assertEquals(result.nbCreeks(),0);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),"idsite");
        result.putResults("{\"cost\": 5, \"extras\": { \"biomes\": [\"SNOW\"], \"creeks\": [\"idcreek\"], \"sites\": []}, \"status\": \"OK\"}");
        int cost2 = 5;
        assertEquals(result.getCost(),cost2);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
        assertEquals(result.getBiome(0),"SNOW");
        assertNotEquals(result.getBiome(1),"SNOW");
        assertEquals(result.nbBiomes(),1);
        assertEquals(result.nbCreeks(),1);
        assertEquals(result.getCreeks(0),"idcreek");
        assertEquals(result.getSites(),null);
    }

    @Test
    public void testLandResult(){
        result = new Land("id",12);
        result.putResults("{ \"cost\": 15, \"extras\": { }, \"status\": \"OK\" }");
        int cost = 15;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
        assertEquals(result.getBiome(0),null);
        assertEquals(result.nbBiomes(),-1);
        assertEquals(result.nbCreeks(),-1);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),null);
    }


    @Test
    public void testStopResult(){
        result = new Stop();
        result.putResults("{ \"cost\": 32, \"extras\": { }, \"status\": \"OK\" }");
        int cost = 32;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
        assertEquals(result.getBiome(0),null);
        assertEquals(result.nbBiomes(),-1);
        assertEquals(result.nbCreeks(),-1);
        assertEquals(result.getCreeks(1),null);
        assertEquals(result.getSites(),null);
    }


}
