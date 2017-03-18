package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.MockedResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexandre Clement
 * @since 05/03/2017.
 */
public class AdvancedStrategyTest
{
    private static final long TIME_OUT = 2000L;
    private static final String TIME_OUT_MESSAGE = "Time out";

    private Protocol advancedStrategy() throws Exception
    {
        Context context = mock(Context.class);
        Basket basket = new Basket();
        basket.put(Resource.FISH, 1000);
        basket.put(Resource.GLASS, 50);
        Contract contracts = new Contract(basket);

        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContract()).thenReturn(contracts);
        return new AdvancedStrategy(context, new IslandMap(), new Drone(context.getHeading()));
    }

    @Test
    public void run() throws Exception
    {
        for (int i = 0; i < 10000; i++)
        {
            Protocol advanced = advancedStrategy();
            Decision decision = advanced.takeDecision();

            while (decision.getActionEnum() != ArgActions.STOP)
            {
                assertNotNull(decision);
                advanced = acknowledgeResults(advanced);
                decision = getDecision(advanced);
            }
        }
    }

    private Decision getDecision(Protocol advanced)
    {
        Decision decision;
        long time = System.currentTimeMillis();
        decision = advanced.takeDecision();
        long end = System.currentTimeMillis() - time;
        if (end > TIME_OUT)
            fail(TIME_OUT_MESSAGE + ": " + end + "ms");
        return decision;
    }

    private Protocol acknowledgeResults(Protocol advanced)
    {
        long time = System.currentTimeMillis();
        advanced = advanced.acknowledgeResults(new MockedResult());
        long end = System.currentTimeMillis() - time;
        if (end > TIME_OUT)
            fail(TIME_OUT_MESSAGE + ": " + end + "ms");
        return advanced;
    }
}