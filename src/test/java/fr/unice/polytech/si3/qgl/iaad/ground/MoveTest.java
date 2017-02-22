package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.Move_to;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class MoveTest {

    private Move move;
    private int distance;
    private Direction direction;
    private HashMap<Resource,Integer>contrat=new HashMap<>();
    private Ground ground;

    @Before
    public void Init(){
        distance=10;
        direction=Direction.N;
        ground = new Move_to(direction.N);
        move = new Move(distance, direction,contrat);
    }

    @Test
    public void nextAction()
    {
        for (int i = 0; i < 10; i++) {
            assertEquals(move.nextAction().getClass(),new Move_to(Direction.N).getClass());
        }
    }

    @Test
    public void setResult()
    {
        assertEquals(move.setResult((ground).putResults("")).getClass(),new Move(distance-1,direction,contrat).getClass());
        distance=0;
        move = new Move(distance, direction,contrat);
        move.nextAction();
        assertEquals(move.setResult((ground).putResults("")).getClass(),new InitialisationGround(contrat,Direction.N).getClass());

    }
}
