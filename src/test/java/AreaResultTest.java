import org.junit.Before;
import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by user on 20/11/2016.
 */
public class AreaResultTest {
    AreaResult result;
    @Before
    public void defineContext() {

    }

    @Test
    public void testFlyResult(){
        result = new FlyResult("\n" +
                "    { \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }\n");
        int cost = 2;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
    }

    @Test
    public void testEchoResult(){
        result = new EchoResult("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        int cost = 1, range = 0;
        String status = "OK",found = "OUT_OF_RANGE";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),range);
        assertEquals(result.getFound(),found);
    }

    @Test
    public void testHeadingResult(){
        result = new HeadingResult("\n" +
                "    { \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }\n");
        int cost = 4;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
        assertEquals(result.getFound(),null);
    }

    @Test
    public void testScanResult(){
        result = new HeadingResult("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");
        int cost = 2;
        String status = "OK";
        assertEquals(result.getCost(),cost);
        assertEquals(result.getStatus(),status);
        assertEquals(result.getRange(),-1);
    }
}
