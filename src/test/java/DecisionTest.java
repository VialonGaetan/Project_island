import fr.unice.polytech.qgl.iaad.Action;
import fr.unice.polytech.qgl.iaad.Decision;
import static org.junit.Assert.*;

import fr.unice.polytech.qgl.iaad.Direction;
import org.junit.Test;

/**
 * @author Alexandre Clement
 *         Created the 13/11/2016.
 */
public class DecisionTest {

    @Test
    public void getDecisionTest() {
        assertEquals("{}", new Decision().getDecision());
    }

    @Test
    public void putActionTest() {
        assertEquals("{\"action\":\"fly\"}", new Decision().putAction(Action.FLY).getDecision());
    }

    @Test
    public void putParametersTest() {
        assertEquals("{\"parameters\":{\"direction\":\"N\"}}", new Decision().putParameters(Direction.N).getDecision());
    }
}
