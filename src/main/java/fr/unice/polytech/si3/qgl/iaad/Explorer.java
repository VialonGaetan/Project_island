package fr.unice.polytech.si3.qgl.iaad;


import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.aerial.*;
import fr.unice.polytech.si3.qgl.iaad.init.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.*;
import org.json.JSONObject;


public class Explorer implements IExplorerRaid {

    private int budget;
    private IslandMap islandMap;
    private Context context;
    private String decision;
    private Drone drone;
    private Action action;
    private String rapport;

    @Override
    public void initialize(String s) {
        islandMap=new IslandMap();
        context = new Context(new JSONObject(s));
        budget = context.getBudget();
        drone = new Drone(budget, Direction.valueOf(context.getHeading()), islandMap);
    }

    @Override
    public String takeDecision()
    {
        try {
            action = drone.doAction();
        } catch (InvalidMapException exception) {
            action =  new Stop();
        }
        decision = action.toJSON();
        return decision;
    }

    /*
     * decision taken
     */
    @Override
    public void acknowledgeResults(String s) {

        try {
            drone.getResult(((Area)action).getResults(s));
        } catch (InvalidMapException exception) {
            // according to map designer, it's ok
        }
        rapport = ((Area) action).getRapport();
    }

    /*
     * result
     */
    @Override
    public String deliverFinalReport() {
        return rapport;
    }

}
