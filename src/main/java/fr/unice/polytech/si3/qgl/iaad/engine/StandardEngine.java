package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.protocol.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.strategy.naive.NaiveStrategy;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public class StandardEngine implements Engine
{
    private static final String REPORT = "We're gonna be rich !";
    private static final int LOW_BUDGET = 200;
    private Protocol protocol;
    private int budget;
    private Context context;

    @Override
    public void setContext(Context context)
    {
        budget = context.getBudget();
        this.context = context;
        protocol = new NaiveStrategy(context);
    }

    @Override
    public Decision takeDecision()
    {
        if (lowBudget() || completedAllContracts())
            protocol = new StopExploration();
        return protocol.takeDecision();
    }

    @Override
    public void acknowledgeResults(Result result)
    {
        budget -= result.getCost();
        protocol.acknowledgeResults(result);
    }

    @Override
    public String deliverFinalReport()
    {
        return REPORT;
    }

    private boolean lowBudget()
    {
        return budget < LOW_BUDGET;
    }

    private boolean completedAllContracts()
    {
        return context.getContracts().stream().allMatch(Contract::isComplete);
    }
}
