import static org.junit.Assert.*;

import fr.unice.polytech.qgl.iaad.AnswersQuery;
import org.json.JSONObject;
import org.junit.Test;

import fr.unice.polytech.qgl.iaad.Drone;

/**
 * @author Alexandre Clement
 *         Created the 13/11/2016.
 */
public class DroneTest {

    @Test
    public void takeFirstDecisionTest() {
        Drone drone = new Drone().setDirection("N");
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}", drone.takeFirstDecision().getDecision());
    }

    @Test
    public void TakeDecisionTest() {
        Drone drone = new Drone().setDirection("N");
        assertEquals("{\"action\":\"stop\"}", drone.takeDecision(drone.takeFirstDecision(), new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }"))).getDecision());
    }
}
