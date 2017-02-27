package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.StandardContract;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class EngineTest
{
    private Engine engine;
    private Context context;
    private List<Contract> contracts;

    @Before
    public void setUp() throws Exception
    {
        engine = new Engine();
        context = mock(Context.class);
        contracts = new ArrayList<>();
        contracts.add(new StandardContract(Resource.FISH, 1000));
        contracts.add(new StandardContract(Resource.GLASS, 50));

        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Direction.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContracts()).thenReturn(contracts);
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
        when(context.getContracts()).thenReturn(new ArrayList<>());
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
        contracts.get(0).collect(1000);
        assertNotEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());
    }

    @Test
    public void allContractsAreComplete() throws Exception
    {
        engine.setContext(context);
        contracts.get(0).collect(1000);
        contracts.get(1).collect(50);
        assertEquals(ArgActions.STOP, engine.takeDecision().getActionEnum());

    }
}