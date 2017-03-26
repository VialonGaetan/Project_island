package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

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
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 20/03/17.
 */
public class ScanIslandTest
{
    private ScanIsland scanIsland;
    private IslandMap islandMap;
    private Drone drone;

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

    @Test
    public void takeDecision()
    {
        assertTrue(scanIsland.takeDecision() instanceof Scan);
    }

    @Test
    public void land()
    {
        islandMap.getTile(new Point(2, 0)).setAsAlready(GroundActionTile.SCANNED);
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(1, Biome.ALPINE, true)) instanceof LandOnCreek);
        assertFalse(scanIsland.acknowledgeResults(newResult(1, Biome.ALPINE, false)) instanceof LandOnCreek);
    }

    @Test
    public void returnToIsland()
    {
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(1, Biome.OCEAN, true)) instanceof ReturnToIsland);
    }

    @Test
    public void fly()
    {
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(1, Biome.ALPINE, true)) instanceof FlyOnMap);
        scanIsland = new ScanIsland(newContext(), islandMap, drone, Compass.E);
        assertTrue(scanIsland.acknowledgeResults(newResult(2, Biome.OCEAN, true)) instanceof FlyOnMap);
    }
}
