package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.GlimpseInformation;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 20/03/17.
 */
public class ScanIslandTest
{
    private ScanIsland scanIsland;
    private IslandMap islandMap;
    private Drone drone;

    /**
     *
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
     * @param moreThanOneBiome : If there is more than 1 biome here
     * @param biome : a given biome
     * @param creek : if there is a creek
     * @return : a new Result
     */
    private Result newResult(boolean moreThanOneBiome, Biome biome, boolean creek)
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

                if(moreThanOneBiome)
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
    public void setUp()
    {
        islandMap = new IslandMap();

        for(Compass compass : Compass.values())
        {
            islandMap.grow(compass, 10);
        }

        drone = new Drone(Compass.E);
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
    }

    /**
     * Check if the returned decision is an Echo
     */
    @Test
    public void takeDecision()
    {
        assertTrue(scanIsland.takeDecision() instanceof Scan);
    }

    /**
     * Check if the crew Land on an allowed position : if there s a creek
     */
    @Test
    public void land()
    {
        islandMap.getTile(new Point(2, 0)).setAsAlready(GroundActionTile.SCANNED);
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(false, Biome.ALPINE, true)) instanceof LandOnCreek);
        assertFalse(scanIsland.acknowledgeResults(newResult(false, Biome.ALPINE, false)) instanceof LandOnCreek);
    }

    /**
     * Check if the returned Protocol is an instance of ReturnToIsland one
     */
    @Test
    public void returnToIsland()
    {
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(false, Biome.OCEAN, true)) instanceof ReturnToIsland);
    }

    @Test
    public void fly()
    {
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(false, Biome.ALPINE, true)) instanceof FlyOnMap);
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(true, Biome.OCEAN, true)) instanceof FlyOnMap);
    }
}
