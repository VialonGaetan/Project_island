package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.init.*;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;
import java.lang.reflect.*;
import org.json.JSONObject;


public class Explorer implements IExplorerRaid {

    private Context context;
    private String decision;
    private Action action;
    private Drone drone;

    @Override
    public void initialize(String s) {
        context = new Context(new JSONObject(s));
        drone = new Drone(this, context.getBudget(), Direction.valueOf(context.getHeading()));
    }

    @Override
    public String takeDecision() {
        action = drone.doAction();
        decision = action.toJSON();
        return decision;
    }

    @Override
    public void acknowledgeResults(String s) {
        drone.getResult(s);
    }

    @Override
    public String deliverFinalReport() {
        return "Nous allons être riche !!";
    }
}
