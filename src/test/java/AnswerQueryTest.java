import fr.unice.polytech.qgl.iaad.AnswersQuery;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gaetan VIALON on 10/11/2016.
 */
public class AnswerQueryTest {
    AnswersQuery Query;
    JSONObject AnswerStopAction;

    @Before
    public void defineContext() {
        AnswerStopAction = new JSONObject("\n" +
                "    { \"cost\": 3, \"extras\": {}, \"status\": \"OK\" }\n");
        Query = new AnswersQuery(AnswerStopAction);
    }

    @Test
    public void TestCosts(){
        int Costs = 3;
        assertEquals(Costs, Query.GetCost());
    }

    @Test
    public void TestStatus(){
        String Status = "OK";
        assertEquals(Status, Query.GetStatus());
    }
}
