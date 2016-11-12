package fr.unice.polytech.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import java.util.*;


public class Explorer implements IExplorerRaid {

    private Context context;
    private final Stack<Decision> decisions = new Stack<>();
    private final Stack<AnswersQuery> results = new Stack<>();
    private final Drone drone = new Drone();


    @Override
    public void initialize(String s) {
        context = new Context(new JSONObject(s));
    }

    @Override
    public String takeDecision() {
        Decision decision;
        if (decisions.isEmpty())
            decision = drone.setDirection(context.GetHeading()).takeFirstDecision();
        else
            decision = drone.takeDecision(decisions.peek(), results.peek());
        decisions.push(decision);
        return decision.getDecision();
    }

    @Override
    public void acknowledgeResults(String s) {
        results.push(new AnswersQuery(new JSONObject(s)));
    }

    @Override
    public String deliverFinalReport() {
        return null;
    }
}
