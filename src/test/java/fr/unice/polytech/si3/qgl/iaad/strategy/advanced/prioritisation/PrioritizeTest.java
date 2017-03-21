package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.MockedResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.AdvancedStrategy;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Test;

import java.awt.*;
import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class PrioritizeTest
{

    private IslandMap islandMap;
    private Contract contracts;

    @Test
    public void prioritisation() throws Exception
    {
        for (int i = 0; i < 2000; i++)
        {
            run();
            ModeledMap modeledMap = new ModeledMap(islandMap);
            Prioritize prioritize = new Prioritize(modeledMap, contracts);
            assertTrue(prioritize.getPriority(PrimaryResource.FRUITS) <= 0);
            assertTrue(prioritize.getPriority(Manufactured.RUM) <= 0);
        }
    }

    private Protocol advancedStrategy() throws Exception
    {
        Context context = mock(Context.class);
        Basket basket = new Basket();
        basket.put(fr.unice.polytech.si3.qgl.iaad.util.resource.Resource.FISH, 1000);
        basket.put(fr.unice.polytech.si3.qgl.iaad.util.resource.Resource.WOOD, 12000);
        basket.put(fr.unice.polytech.si3.qgl.iaad.util.resource.Resource.GLASS, 50);
        contracts = new Contract(basket);

        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContract()).thenReturn(contracts);
        islandMap = new IslandMap();
        return new AdvancedStrategy(context, islandMap, new Drone(context.getHeading()));
    }

    private void run() throws Exception
    {
        Protocol advanced = advancedStrategy();
        Decision decision = advanced.takeDecision();

        while (decision.getActionEnum() != ArgActions.STOP)
        {
            advanced = advanced.acknowledgeResults(new MockedResult());
            decision = advanced.takeDecision();
        }
    }
}