package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class EngineTest
{
    private Engine engine;
    private Context context;
    private Contract contracts;

    @Before
    public void setUp() throws Exception
    {
        engine = new Engine();
        context = mock(Context.class);
        Basket basket = new Basket();
        basket.put(Resource.FISH, 1000);
        basket.put(Resource.GLASS, 50);
        contracts = new Contract(basket);

        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContract()).thenReturn(contracts);
    }

    @Test
    public void decisionAreNotNullable() throws Exception
    {
        engine.setContext(context);
        assertNotNull(engine.takeDecision());
    }

    @Test
    public void notEnoughBudget() throws Exception
    {
        when(context.getBudget()).thenReturn(199);
        engine.setContext(context);
        assertEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void emptyContracts() throws Exception
    {
        when(context.getContract()).thenReturn(new Contract(new Basket()));
        engine.setContext(context);
        assertEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void enoughBudgetAndAtLeastOneContract() throws Exception
    {
        engine.setContext(context);
        assertNotEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void deductActionCostFromTheBudget() throws Exception
    {
        when(context.getBudget()).thenReturn(200);

        engine.setContext(context);
        engine.takeDecision();

        Result result = mock(Result.class);
        when(result.getCost()).thenReturn(1);

        engine.acknowledgeResults(result);
        assertEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void notAllContractAreCompleted() throws Exception
    {
        engine.setContext(context);
        contracts.collect(Resource.FISH, 1000);
        assertNotEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void allContractsAreComplete() throws Exception
    {
        engine.setContext(context);
        contracts.collect(Resource.FISH, 1000);
        contracts.collect(Resource.GLASS, 50);
        assertEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());

    }
}