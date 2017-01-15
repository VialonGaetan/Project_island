package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class StopAerialTest
{
    private StopAerial stopAerial;

    @Before
    public void setUp() throws Exception
    {
        stopAerial = new StopAerial();
    }

    @Test
    public void nextAction() throws Exception
    {
        assertTrue(stopAerial.nextAction() instanceof Stop);
    }

    @Test
    public void setResult() throws Exception
    {
        Area area = ((Area) stopAerial.nextAction()).putResults(new JSONObject().toString());
        assertNull(stopAerial.setResult(area));
    }

}