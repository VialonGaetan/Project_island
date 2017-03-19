package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Gaetan Vialon
 *         Created the 18/03/2017.
 */
public class GoToAPointTest {

    IslandMap map;
    Crew crew;
    Point destination;
    Map<Resource, Integer> contrat;

    @Before
    public void init(){

        map = new IslandMap();
        map.grow(Compass.N,0);
        map.grow(Compass.S,10);
        map.grow(Compass.E,10);
        map.grow(Compass.W,0);
        crew = new Crew(1,new Point(1,1));
        destination = new Point(3,2);
        contrat = new HashMap();
        contrat.put(Resource.FISH,12);
    }


    @Test
    public void run(){
        Protocol strategy = new GoToAPoint(destination,contrat,crew,map);
        Decision decision = strategy.takeDecision();
        //assertEquals(decision, new Move_to(Compass.S));
        assertEquals(crew.getLocation(),new Point(1,2));

        strategy = strategy.acknowledgeResults(mock(Result.class));
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(2,2));

        strategy = strategy.acknowledgeResults(mock(Result.class));
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(3,2));

        strategy = strategy.acknowledgeResults(mock(Result.class));
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(3,2));

        destination = new Point(1,0);

        strategy = new GoToAPoint(destination,contrat,crew,map);
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(3,1));

        strategy = strategy.acknowledgeResults(mock(Result.class));
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(3,0));

        strategy = strategy.acknowledgeResults(mock(Result.class));
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(2,0));

        strategy = strategy.acknowledgeResults(mock(Result.class));
        decision = strategy.takeDecision();
        assertEquals(crew.getLocation(),new Point(1,0));
    }
}
