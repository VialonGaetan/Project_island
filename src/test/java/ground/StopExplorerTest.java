package ground;

import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.StopGround;
import fr.unice.polytech.si3.qgl.iaad.ground.StopExplorer;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class StopExplorerTest {

    private StopExplorer finishGame;

    @Before
    public void Init(){
        finishGame = new StopExplorer();
    }

    @Test
    public void nextAction()
    {
        assertEquals(finishGame.nextAction().getClass(),new StopGround().getClass());
    }

    @Test
    public void setResult()
    {
        Ground ground = ((Ground) finishGame.nextAction()).putResults(new JSONObject().toString());
        assertNull(finishGame.setResult(ground));
    }
}
