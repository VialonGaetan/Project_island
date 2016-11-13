import fr.unice.polytech.si3.qgl.iaad.*;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by user on 07/11/2016.
 */
public class ExplorerTest {
    Explorer explorer = new Explorer();


    @Test
    public void TestFirstDecision(){
        explorer.initialize(setContrat(12, 10000, "W", setResource(600, "WOOD"), setResource(200, "GLASS")).toString());
        String firstDecision = "{\"action\":\"echo\",\"parameters\":{\"direction\":\"W\"}}";
        assertEquals(firstDecision, explorer.takeDecision());
    }

    @Test
    public void TestFirstDecisionNorthHeading(){
        explorer.initialize(setContrat(12, 10000, "N", setResource(600, "WOOD"), setResource(200, "GLASS")).toString());
        String firstDecision = "{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}";
        assertEquals(firstDecision, explorer.takeDecision());
    }

    @Test
    public void TestStop(){
        explorer.initialize(setContrat(12, 2, "N", setResource(600, "WOOD"), setResource(200, "GLASS")).toString());
        explorer.takeDecision();
        explorer.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        assertEquals("{\"action\":\"stop\"}", explorer.takeDecision());
    }

    public JSONObject setResource(int amount, String resource) {
        return new JSONObject().put("amount", amount).put("resource", resource);
    }

    public JSONObject setContrat(int men, int budget, String heading, JSONObject... resources) {
        JSONArray contracts = new JSONArray();
        for (JSONObject resource: resources)
            contracts.put(resource);

        return new JSONObject()
                .put("men", men)
                .put("budget", budget)
                .put("contracts", contracts)
                .put("heading", heading);
    }


}
