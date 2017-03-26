package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 24/03/2017.
 */
public class PrioritisationTest {

    private Contract contract;
    private IslandMap map;
    private Area area;
    private Crew crew;
    private SimulatedMap simulatedMap;
    private Prioritisation prioritisation;
    private Optional<Point> destination;

    @Before
    public void setUp() throws Exception {
        setUpMap();
        setUpContract1();
        area = new Area(map);
        crew = new Crew(1,new Point(2,2));
        simulatedMap = new SimulatedMap(map);
        prioritisation = new Prioritisation(contract,map,simulatedMap,area,crew);
    }

    @Test
    public void prioritisationTest() throws Exception{
        destination = Optional.empty();
        prioritisation = new Prioritisation(new Contract(),map,simulatedMap,area,crew);
        assertEquals(prioritisation.nextLocationToExploit(),destination);//if no contract no destination


        prioritisation = new Prioritisation(contract,map,simulatedMap,area,crew);
        destination=Optional.of(new Point(2,1));
        assertEquals(crew.getLocation(),new Point(2,2));
        assertEquals(prioritisation.nextLocationToExploit(),destination);//First Contract fish is easier than plank so
        // he go to Ocean to collect fish

        setUpContract2();//Plank is easier than fish
        crew = new Crew(1,new Point(0,0));
        prioritisation = new Prioritisation(contract,map,simulatedMap,area,crew);
        destination=Optional.of(new Point(1,1));
        assertEquals(crew.getLocation(),new Point(0,0));
        assertEquals(prioritisation.nextLocationToExploit(),destination);
        map.getTile(new Point(1,1)).setAsAlready(GroundActionTile.VISITED);// si la case est deja visité il va plutot à celle à coté
        destination=Optional.of(new Point(2,1));
        assertEquals(prioritisation.nextLocationToExploit(),destination);

        setUpContract3();//No ressource on map
        prioritisation = new Prioritisation(contract,map,simulatedMap,area,crew);
        destination=Optional.empty();
        assertEquals(prioritisation.nextLocationToExploit(),destination);

    }

    @Test
    public void resourceOnTitle(){
        setUpContract2();//Plank is easier than fish
        crew = new Crew(1,new Point(2,2));
        prioritisation = new Prioritisation(contract,map,simulatedMap,area,crew);
        destination=Optional.of(new Point(2,2));
        assertEquals(crew.getLocation(),new Point(2,2));
        assertEquals(destination, prioritisation.nextLocationToExploit());
        map.getTile(new Point(1,1)).setAsAlready(GroundActionTile.VISITED);// si la case est deja visité il va plutot à celle à coté
        destination=Optional.of(new Point(2,2));
        assertEquals(prioritisation.nextLocationToExploit(),destination);

    }

    @Test
    public void AreaTest() throws Exception {
        assertEquals(area.getCluster(Biome.TEMPERATE_DECIDUOUS_FOREST).getCenter(),new Point(5,5)); //centre du biome FOREST
        assertEquals(area.getCluster(Biome.TEMPERATE_DECIDUOUS_FOREST).area(),9*9);//Forest present in 9*9 in map
        assertEquals(area.getArea(Biome.TEMPERATE_DECIDUOUS_FOREST),9*9);
        assertEquals(area.getCluster(Biome.OCEAN).getCenter(),new Point(5,4)); // Cause 2 lines OCEAN in top of map
        assertEquals(area.getArea(Biome.OCEAN),(3*11)+(2*8));//Ocean is present on 3 line Vertical + 2 Horizontal
        assertEquals(area.getCluster(Biome.OCEAN).area(),(3*11)+(2*8));//
    }


    private void setUpContract1(){
        contract = new Contract();
        contract.addContract(new PrimaryContract(PrimaryResource.FISH,20));
        contract.addContract(new ManufacturedContract(Manufactured.PLANK,40000));
    }

    private void setUpContract2(){
        contract = new Contract();
        contract.addContract(new PrimaryContract(PrimaryResource.FISH,20000));
        contract.addContract(new ManufacturedContract(Manufactured.PLANK,4));
    }

    private void setUpContract3(){
        contract = new Contract();
        contract.addContract(new PrimaryContract(PrimaryResource.ORE,2000));
    }

    private void setUpMap(){

        map = new IslandMap();
        List <Biome> biomes = new ArrayList<>();
        biomes.add(Biome.OCEAN);
        map.grow(Compass.N,0);
        map.grow(Compass.W,0);
        map.grow(Compass.S,10);
        map.grow(Compass.E,10);

        for (int i = 0; i < 11; i++) { //surrend map by OCEAN
            for (int j = 0; j < 2; j++) {
                map.getTile(new Point(i,j)).addBiomes(biomes);
            }
        }

        for (int i = 0; i < 11; i++) {
            map.getTile(new Point(0,i)).addBiomes(biomes);
        }

        for (int i = 0; i < 11; i++) {
            map.getTile(new Point(i,10)).addBiomes(biomes);
        }

        for (int i = 0; i < 11; i++) {
            map.getTile(new Point(10,i)).addBiomes(biomes);
        }

        biomes.clear();
        biomes.add(Biome.TEMPERATE_DECIDUOUS_FOREST);
        for (int i = 1; i < 10 ; i++) {
            for (int j = 1; j <10 ; j++) {
                map.getTile(new Point(i,j)).addBiomes(biomes);
            }
        }

    }
}