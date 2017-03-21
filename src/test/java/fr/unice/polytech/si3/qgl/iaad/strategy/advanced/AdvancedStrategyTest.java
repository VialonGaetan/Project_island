package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Oriented;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.MockedResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexandre Clement
 * @since 05/03/2017.
 */
public class AdvancedStrategyTest
{
    private Drone expectedDrone;

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
        expectedDrone = new Drone(context.getHeading());
        return new AdvancedStrategy(context, new IslandMap(), new Drone(context.getHeading()));
    }

    @Test
    public void timeout() throws Exception
    {
        run(new TimeOut());
    }

    @Test
    public void echoHaveGoodOrientation() throws Exception
    {
        Assertion goodOrientation = decision ->
        {
            if (decision.getActionEnum() == ArgActions.ECHO)
                assertFalse(expectedDrone.getHeading() == ((Oriented) decision).getDirection().get(Direction.BACK));
            else if (decision.getActionEnum() == ArgActions.HEADING)
                expectedDrone.heading(((Oriented) decision).getDirection());
        };
        run(goodOrientation);
    }

    @Test
    public void headingHaveGoodOrientation() throws Exception
    {
        Assertion goodOrientation = decision ->
        {
            if (decision.getActionEnum() == ArgActions.HEADING)
            {
                assertTrue(expectedDrone.getHeading().isOrthogonal(((Oriented) decision).getDirection()));
                expectedDrone.heading(((Oriented) decision).getDirection());
            }
        };
        run(goodOrientation);
    }

    private void run(Assertion assertion) throws Exception
    {
        for (int i = 0; i < 2000; i++)
        {
            Protocol advanced = advancedStrategy();
            Decision decision = advanced.takeDecision();

            while (decision.getActionEnum() != ArgActions.STOP)
            {
                assertion.valid(decision);
                advanced = advanced.acknowledgeResults(new MockedResult());
                decision = advanced.takeDecision();
            }
        }
    }
}