import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Gaetan Vialon
 * Created the 20/11/2016.
 */

public class AreaResultTest {

    private AreaResult result;

    @Before
    public void defineContext() {}

    @Test
    public void testFlyResult(){
        result = new FlyResult("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }\n");
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
        result = new EchoResult("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
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
        result = new EchoResult("{ \"cost\": 10, \"extras\": { \"range\": 55, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
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
        result = new HeadingResult("{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }\n");
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
        result = new ScanResult("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"idsite\"]}, \"status\": \"OK\"}");
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
        result = new ScanResult("{\"cost\": 5, \"extras\": { \"biomes\": [\"SNOW\"], \"creeks\": [\"idcreek\"], \"sites\": []}, \"status\": \"OK\"}");
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
}
