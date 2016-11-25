package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.init.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.AddPointsException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;
import java.lang.reflect.*;
import org.json.JSONObject;


public class Explorer implements IExplorerRaid {

    private int budget;
    private IslandMap islandMap;
    private Context context;
    private String decision;
    private Drone drone;
    private Action action;

    @Override
    public void initialize(String s) {
        islandMap=new IslandMap();
        context = new Context(new JSONObject(s));
        budget = context.getBudget();
        drone = new Drone(Direction.valueOf(context.getHeading()), islandMap);
    }

    @Override
    public String takeDecision()
    {
        action = drone.doAction();
        decision = action.toJSON();
        return decision;
    }

    /*
     * decision taken
     */
    @Override
    public void acknowledgeResults(String s) {
        drone.getResult(((Area)action).getResults(s));
    }

    /*
     * result
     */
    @Override
    public String deliverFinalReport() {
        return "Nous allons être riche !!";
    }
}
