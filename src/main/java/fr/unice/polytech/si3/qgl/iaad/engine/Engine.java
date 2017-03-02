package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.strategy.simple.NaiveStrategy;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public class Engine
{
    private static final String REPORT = "We're gonna be rich !";
    private static final int LOW_BUDGET = 200;
    private Protocol protocol;
    private int budget;
    private Context context;

    public void setContext(Context context)
    {
        budget = context.getBudget();
        this.context = context;
        protocol = new NaiveStrategy(context);
    }

    public Decision takeDecision()
    {
        if (lowBudget() || completedAllContracts())
            protocol = new StopExploration();
        return protocol.takeDecision();
    }

    public void acknowledgeResults(Result result)
    {
        budget -= result.getCost();
        protocol = protocol.acknowledgeResults(result);
    }

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
