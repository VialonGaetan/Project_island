package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Turn;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.*;
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
public class AlongCoastsTest {

    private IslandMap islandMap;
    private Drone drone;
    private Protocol exit;
    private AlongCoasts alongCoasts;


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

    private Result newResult(int n, Biome biome, boolean creek, int range, Element element)
    {
        return new Result()
        {
            @Override
            public int getCost() { return 60; }

            @Override
            protected int getRange() { return range; }

            @Override
            protected Element getFound() { return element; }

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
            protected List<ResourceInformation> getResourcesExplored() {
                List<ResourceInformation> exploredResources = new ArrayList<>();
                exploredResources.add(new ResourceInformation(PrimaryResource.FISH, ResourceAmount.HIGH, ResourceCondition.EASY));
                exploredResources.add(new ResourceInformation(PrimaryResource.WOOD, ResourceAmount.MEDIUM, ResourceCondition.HARSH));
                return exploredResources;
            }

            @Override
            protected int getExploitAmount() { return 25; }

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

        exit = new ScanIsland(newContext(),islandMap,drone,Compass.E);
        alongCoasts = new AlongCoasts(exit, newContext(), islandMap,drone,Compass.E);
    }

    @Test
    public void takedecisionTest(){
        assertTrue(this.alongCoasts.takeDecision() instanceof Echo);
    }

    @Test
    public void acknoledgeResultTest(){
        Result result = newResult(1,Biome.ALPINE, false, 2, Element.GROUND);
        assertTrue(this.alongCoasts.acknowledgeResults(result) instanceof FlyOnMap);
        result = newResult(1,Biome.ALPINE, false, 1, Element.GROUND);
        assertTrue(this.alongCoasts.acknowledgeResults(result) instanceof FlyOnMap);
        result = newResult(1,Biome.ALPINE, false, 3, Element.GROUND);
        assertFalse(this.alongCoasts.acknowledgeResults(result) instanceof FlyOnMap);
        assertTrue(this.alongCoasts.acknowledgeResults(result) instanceof Turn);
        result = newResult(1,Biome.ALPINE, false, 4, Element.GROUND);
        assertFalse(this.alongCoasts.acknowledgeResults(result) instanceof FlyOnMap);
        assertTrue(this.alongCoasts.acknowledgeResults(result) instanceof Turn);
        result = newResult(1, Biome.ALPINE, false,2, Element.OUT_OF_RANGE);
        assertTrue(this.alongCoasts.acknowledgeResults(result) instanceof Turn);
        result = newResult(10,Biome.BEACH, true, 1, Element.OUT_OF_RANGE);
        assertTrue(this.alongCoasts.acknowledgeResults(result) instanceof Turn);

    }

}
