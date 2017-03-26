package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Gaetan Vialon
 *         Created the 26/03/2017.
 */

public class MoveCrewToTest {

    private Context context;
    private IslandMap map;
    private Crew crew;
    private Point destination;
    private Protocol protocol;

    @Before
    public void setUp() throws Exception {
        context = mock(Context.class);
    }

    @Test
    public void MoveCrewInEasyMap(){
        destination = new Point(3,4);
        crew = new Crew(1,new Point(0,0));
        setUpFirstMap();
        protocol = new MoveCrewTo(context,map,crew,destination);
        assertEquals(crew.getLocation(),new Point(0,0));
        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(1,0));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(2,0));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(3,0));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(3,1));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(3,2));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(3,3));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertEquals(crew.getLocation(),new Point(3,4));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.EXPLORE);
        assertEquals(crew.getLocation(),new Point(3,4));
    }

    @Test
    public void MoveCrewInSecondMap(){
        destination = new Point(2,1);
        crew = new Crew(1,new Point(0,0));
        setUpSecondMap();
        protocol = new MoveCrewTo(context,map,crew,destination);
        assertEquals(crew.getLocation(),new Point(0,0));
        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        assertTrue(Biome.ALPINE.getCrossFactor() > Biome.BEACH.getCrossFactor());
        //assertEquals(crew.getLocation(),new Point(0,1));//Beach is easier than alpine biome
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        //assertEquals(crew.getLocation(),new Point(1,1));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.MOVE_TO);
        //assertEquals(crew.getLocation(),new Point(2,1));
        protocol = protocol.acknowledgeResults(mock(Result.class));

        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.EXPLORE);
        assertEquals(crew.getLocation(),new Point(2,1));
    }

    private void setUpFirstMap(){
        //define map 5*5
        map = new IslandMap();
        map.grow(Compass.N,0);
        map.grow(Compass.W,0);
        map.grow(Compass.S,4);
        map.grow(Compass.E,4);
        List<Biome> biomeList = new ArrayList<>();
        biomeList.add(Biome.TEMPERATE_DESERT);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map.getTile(new Point(i,j)).addBiomes(biomeList);
            }
        }
    }

    private void setUpSecondMap(){
        //define map 3*3
        map = new IslandMap();
        map.grow(Compass.N,0);
        map.grow(Compass.W,0);
        map.grow(Compass.S,2);
        map.grow(Compass.E,2);
        List<Biome> biomeList = new ArrayList<>();
        biomeList.add(Biome.OCEAN);
        map.getTile(new Point(0,0)).addBiomes(biomeList);
        biomeList.clear();
        biomeList.add(Biome.BEACH);
        map.getTile(new Point(0,1)).addBiomes(biomeList);
        map.getTile(new Point(1,1)).addBiomes(biomeList);
        map.getTile(new Point(2,1)).addBiomes(biomeList);
        biomeList.clear();
        biomeList.add(Biome.ALPINE);
        map.getTile(new Point(1,0)).addBiomes(biomeList);
        map.getTile(new Point(2,0)).addBiomes(biomeList);
        map.getTile(new Point(0,2)).addBiomes(biomeList);



    }

}