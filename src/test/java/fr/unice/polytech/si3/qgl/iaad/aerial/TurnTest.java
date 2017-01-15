package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class TurnTest
{
    private Direction target;
    private Turn turn;
    private Turn fail;
    private IslandMap map;
    private Protocol exit;

    @Before
    public void setUp() throws Exception
    {
        exit = new StopAerial();
        map = DroneTest.facticeMap();
        Direction heading = Direction.E;
        target = heading.getRight();
        Direction failTarget = heading.getLeft();
        turn = new Turn(exit, map, heading, target);
        fail = new Turn(exit, map, heading, failTarget);

    }

    @Test
    public void nextAction() throws Exception
    {
        Point position = new Point(map.getLocation());
        position.translate(1, 1);
        Area action = (Area) turn.nextAction();
        assertTrue(action instanceof Heading);
        assertEquals(target, action.direction);
        assertEquals(position, map.getLocation());
    }

    @Test
    public void failNextAction() throws Exception
    {
        assertTrue(fail.nextAction() instanceof Stop);
    }

    @Test
    public void setResult() throws Exception
    {
        Area action = ((Area) turn.nextAction()).putResults(DroneTest.createJSON(10).toString());
        assertEquals(exit, turn.setResult(action));
    }

    @Test
    public void failSetResult() throws Exception
    {
        Area action = ((Area) fail.nextAction()).putResults(DroneTest.createJSON(10).toString());
        assertEquals(exit, fail.setResult(action));
    }

}