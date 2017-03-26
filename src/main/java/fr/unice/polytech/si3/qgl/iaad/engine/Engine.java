package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.AdvancedStrategy;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public class Engine
{
    private static final String REPORT = "We're gonna be rich !";
    private Protocol protocol;
    private Budget budget;
    private Context context;

    public void setContext(Context context)
    {
        budget = context.getBudget();
        this.context = context;
        protocol = new AdvancedStrategy(context, new IslandMap(), new Drone(context.getHeading()));
    }

    public Decision takeDecision()
    {
        if (budget.isLow() || context.getContract().allCompleted())
            protocol = new StopExploration();
        return protocol.takeDecision();
    }

    public void acknowledgeResults(Result result)
    {
        budget.deduce(result.getCost());
        protocol = protocol.acknowledgeResults(result);
    }

    public String deliverFinalReport()
    {
        return REPORT;
    }
}
