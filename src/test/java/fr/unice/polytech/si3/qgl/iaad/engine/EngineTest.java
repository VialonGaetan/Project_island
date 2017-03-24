package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
    private PrimaryContract contracts;
    private Contract contract;

    @Before
    public void setUp() throws Exception
    {
        engine = new Engine();
        context = mock(Context.class);
        contract = new Contract();
        contract.addContract(new PrimaryContract(PrimaryResource.FISH,50));
        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContract()).thenReturn(contract);
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
        when(context.getContract()).thenReturn(new Contract());
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
        assertNotEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void allContractsAreComplete() throws Exception
    {
        when(context.getContract()).thenReturn(new Contract());
        engine.setContext(context);
        assertTrue(new Contract().getTotalBasket().isEmpty());
        assertEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());

    }
}