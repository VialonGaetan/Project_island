package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.Move_to;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 15/01/2017.
 */
public class MoveTest
{
    private Move move;

    /**
     * test if return a new Move_to
     */
    @Test
    public void nexAction()
    {
        move = new Move(0, Direction.E, new HashMap());
        assertTrue(move.nextAction() instanceof Move_to );
    }

    /**
     * test if return a new InitialisationGround
     */
    @Test
    public void setResultInitialisationGround()
    {
        move = new Move(0, Direction.E, new HashMap());
        assertTrue(move.setResult(new Ground() {
            @Override
            public Ground putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof InitialisationGround);
    }

    /**
     * test if return a new Move
     */
    @Test
    public void setResultMove()
    {
        move = new Move(10, Direction.E, new HashMap());
        assertTrue(move.setResult(new Ground() {
            @Override
            public Ground putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof Move);
    }
}