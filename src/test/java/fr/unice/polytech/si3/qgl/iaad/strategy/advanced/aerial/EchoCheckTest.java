package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
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

import static org.junit.Assert.assertFalse;
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

    /**
     * Method to create a Context
     * @return : a new Context
     */
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
            public Budget getBudget()
            {
                return new Budget(80);
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

    /**
     *
     * @param biome : a certain biome
     * @param creek : if there is a creek here or not
     * @param element : an element found
     * @return
     */
    private Result newResult(Biome biome, boolean creek, Element element)
    {
        return new Result()
        {
            @Override
            public int getCost() { return 60; }

            @Override
            protected int getRange() { return 20; }

            @Override
            protected Element getFound() { return element; }

            @Override
            protected List<Biome> getBiomes()
            {
                List<Biome> biomes = new ArrayList<>();
                biomes.add(biome);

                biomes.add(Biome.GRASSLAND);

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

    /**
     * Check if the decision taken by the drone is an Echo
     */
    @Test
    public void takeDecisionTest(){
        assertTrue(this.echoCheck.takeDecision() instanceof Echo);
    }

    /**
     * Check if the protocol returned is the good
     */
    @Test
    public void acknowledgeResultsTest(){
        Result result = newResult(Biome.ALPINE,false, Element.GROUND);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(Biome.OCEAN, false, Element.GROUND);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(Biome.ALPINE, false, Element.GROUND);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(Biome.BEACH,true, Element.GROUND);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(Biome.ALPINE, true, Element.GROUND);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(Biome.TUNDRA, true, Element.GROUND);
        assertTrue(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);
        result = newResult(Biome.BEACH, true, Element.OUT_OF_RANGE);
        assertFalse(this.echoCheck.acknowledgeResults(result) instanceof FlyToIsland);

    }

}
