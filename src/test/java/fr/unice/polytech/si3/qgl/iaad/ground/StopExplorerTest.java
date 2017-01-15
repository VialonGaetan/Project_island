package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 15/01/2017.
 */
public class StopExplorerTest
{
    private StopExplorer stopExplorer;

    /**
     * test if return a new StopGround
     */
    @Test
    public void nexAction()
    {
        stopExplorer = new StopExplorer();
        assertTrue(stopExplorer.nextAction() instanceof StopGround );
    }

    /**
     * test if return a new Stop Explorer
     */
    @Test
    public void setResult()
    {
        stopExplorer = new StopExplorer();
        assertTrue(stopExplorer.setResult(new Ground() {
            @Override
            public Ground putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof StopExplorer);
    }
}
