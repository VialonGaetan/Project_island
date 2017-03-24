package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Gaetan Vialon
 *         Created the 24/03/2017.
 */
public class SimulatedMapTest {

    IslandMap map;
    SimulatedMap simulatedMap;
    List<Biome> biomes;
    Crew crew;

    @Before
    public void setUp() throws Exception {
        biomes = new ArrayList<>();
        map = new IslandMap();
        map.grow(Compass.N,0);
        map.grow(Compass.W,0);
        map.grow(Compass.S,10);
        map.grow(Compass.E,10);

    }

    @Test
    public void simulatedMap() throws Exception {
        simulatedMap = new SimulatedMap(map); //Without Information the simulatedMap is Empty
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j <11 ; j++) {
                assertEquals(simulatedMap.getTile(new Point(i,j)).getBiomes(),biomes);
            }

        }
        biomes.add(Biome.OCEAN);
        map.getTile(new Point(0,0)).addBiomes(biomes);
        simulatedMap = new SimulatedMap(map);//Simulated only near biomes
        assertEquals(simulatedMap.getTile(new Point(1,0)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(0,0)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(0,1)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(1,1)).getBiomes(),biomes);

        biomes.clear();
        for (int i = 1; i <11 ; i++) { //The rest of map is empty
            for (int j = 2; j <11 ; j++) {
                assertEquals(simulatedMap.getTile(new Point(i,j)).getBiomes(),biomes);
            }

        }
        biomes.add(Biome.BEACH);
        map.getTile(new Point(2,2)).addBiomes(biomes);//add all point surrended biome Beach
        simulatedMap = new SimulatedMap(map);
        //map.getTile(new Point(2,2)).addBiomes(biomes);
        assertEquals(simulatedMap.getTile(new Point(1,2)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(1,3)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(2,1)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(2,2)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(2,3)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(3,1)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(3,2)).getBiomes(),biomes);
        assertEquals(simulatedMap.getTile(new Point(3,3)).getBiomes(),biomes);

        biomes.clear();
        biomes.add(Biome.OCEAN);//Execpt 1,1 where BEACH and OCEAN merge
        biomes.add(Biome.BEACH);
        assertEquals(simulatedMap.getTile(new Point(1,1)).getBiomes(),biomes);

        biomes.clear();
        for (int i = 3; i <11 ; i++) { //The rest of map is empty
            for (int j = 4; j <11 ; j++) {
                assertEquals(simulatedMap.getTile(new Point(i,j)).getBiomes(),biomes);
            }

        }

    }
}