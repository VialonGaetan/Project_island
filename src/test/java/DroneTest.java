import static org.junit.Assert.*;

import fr.unice.polytech.qgl.iaad.AnswersQuery;
import fr.unice.polytech.qgl.iaad.Decision;
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
        Drone drone = new Drone().setBudget(10000).setDirection("N");
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}", drone.takeFirstDecision().getDecision());
    }

    @Test
    public void TakeDecisionFoundGroundTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(10000).setDirection("E");

        // turn 1
        decision = drone.takeFirstDecision();
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"E\"}}", drone.takeFirstDecision().getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }"));

        // turn 2
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"fly\"}", decision.getDecision());
    }

    @Test
    public void TakeDecisionFoundOutOfRangeTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(10000).setDirection("E");

        // turn 1
        decision = drone.takeFirstDecision();
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"E\"}}", drone.takeFirstDecision().getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }"));

        // turn 2
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"fly\"}", decision.getDecision());
    }

    @Test
    public void TakeDecisionsStopTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(10000).setDirection("E");

        // turn 1
        decision = drone.takeFirstDecision();
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"E\"}}", drone.takeFirstDecision().getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }"));

        // turn 2
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"stop\"}", decision.getDecision());
    }

    @Test
    public void TakeDecisionTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(10000).setDirection("E");

        // turn 1
        decision = drone.takeFirstDecision();
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"E\"}}", drone.takeFirstDecision().getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"GROUND\" }, \"status\": \"OK\" }"));

        // turn 2
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"fly\"}", decision.getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }"));

        // turn 3
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"E\"}}", decision.getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }"));

        // turn 4
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"stop\"}", decision.getDecision());
    }

    @Test
    public void takeDecisionLoopTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(10000).setDirection("N");

        int range = 5;

        decision = drone.takeFirstDecision();
        JSONObject answer = new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": "+range+", \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");

        while (!decision.getAction().getName().equals("stop")) {
            range -= 1;
            answer = new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": "+range+", \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
            decision = drone.takeDecision(decision, new AnswersQuery(answer));
            }

        assertEquals(0, range);
    }

    @Test
    public void takeFirstDecisionBudgetTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(2).setDirection("N");

        // turn 1
        decision = drone.takeFirstDecision();
        assertEquals("{\"action\":\"stop\"}", drone.takeFirstDecision().getDecision());
    }

    @Test
    public void takeDecisionBudgetTest() {
        Decision decision;
        AnswersQuery answersQuery;
        Drone drone = new Drone().setBudget(3).setDirection("N");

        // turn 1
        decision = drone.takeFirstDecision();
        assertEquals("{\"action\":\"echo\",\"parameters\":{\"direction\":\"N\"}}", drone.takeFirstDecision().getDecision());
        answersQuery = new AnswersQuery(new JSONObject("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"GROUND\" }, \"status\": \"OK\" }"));

        // turn 2
        decision = drone.takeDecision(decision, answersQuery);
        assertEquals("{\"action\":\"stop\"}", decision.getDecision());
    }

}
