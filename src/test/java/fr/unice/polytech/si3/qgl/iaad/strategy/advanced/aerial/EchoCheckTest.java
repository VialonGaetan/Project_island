package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.GlimpseInformation;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Théo on 26/03/2017.
 */
public class EchoCheckTest {

    private IslandMap islandMap;
    private Drone drone;
    private EchoCheck echoCheck;
    private Protocol groundProtocol;
    private Protocol outOfRangeProtocol;

    private Context newContext()
    {
        return new Context()
        {
            @Override
            public int getNumberOfMen()
            {
                return 30;
            }

            @Override
            public int getBudget()
            {
                return 80;
            }

            @Override
            public Contract getContract()
            {
                return new Contract();
            }

            @Override
            public Compass getHeading()
            {
                return Compass.E;
            }
        };
    }

    private Result newResult(int n, Biome biome, boolean creek)
    {
        return new Result()
        {
            @Override
            public int getCost() { return 60; }

            @Override
            protected int getRange() { return 20; }

            @Override
            protected Element getFound() { return null; }

            @Override
            protected List<Biome> getBiomes()
            {
                List<Biome> biomes = new ArrayList<>();
                biomes.add(biome);

                if(n == 2)
                {
                    biomes.add(Biome.GRASSLAND);
                }
                return biomes;
            }

            @Override
            protected List<Creek> getCreeks()
            {
                List<Creek> creeks = new ArrayList<>();

                if(creek)
                {
                    creeks.add(new Creek("id"));
                }

                return creeks;
            }

            @Override
            protected List<EmergencySite> getSites()
            {
                List<EmergencySite> emergencySites = new ArrayList<>();
                emergencySites.add(new EmergencySite("id2"));
                return emergencySites;
            }

            @Override
            protected List<ResourceInformation> getResourcesExplored() { return null; }

            @Override
            protected int getExploitAmount() { return 0; }

            @Override
            protected int getTransformProduction() { return 0; }

            @Override
            protected List<GlimpseInformation> getGlimpseInformation() { return null; }
        };
    }

    @Before
    public void setUp(){

        islandMap = new IslandMap();

        for(Compass compass : Compass.values())
        {
            islandMap.grow(compass, 10);
        }
        drone = new Drone(Compass.E);
        groundProtocol = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        outOfRangeProtocol = new ScanIsland(newContext(), islandMap, drone, Compass.E.get(Direction.BACK));
        echoCheck = new EchoCheck(groundProtocol, outOfRangeProtocol, newContext(), islandMap, drone);
    }

    @Test
    public void takeDecisionTest(){
        assertTrue(this.echoCheck.takeDecision() instanceof Echo);
    }

    @Test
    public void acknowledgeResultsTest(){
        Result result = newResult(10, Biome.ALPINE,false);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(12, Biome.OCEAN, false);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(1,Biome.ALPINE, false);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(5,Biome.BEACH,true);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(5,Biome.ALPINE, true);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(10, Biome.TUNDRA, true);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);

    }

}
