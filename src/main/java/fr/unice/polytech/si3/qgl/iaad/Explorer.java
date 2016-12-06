package fr.unice.polytech.si3.qgl.iaad;


import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.json.JSONObject;


public class Explorer implements IExplorerRaid {

    private int budget;
    private IslandMap islandMap;
    private Context context;
    private String decision;
    private Drone drone;
    private Action action;
    private String rapport;
    private Creek creek;

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
        action = drone.doAction();
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
    }

    /*
     * result
     */
    @Override
    public String deliverFinalReport() {
        try {
            creek = new Creek(islandMap);
            creek.addAllTheCreeks(); //on ajoute toutes les creek recensées dans la Map dans une liste
            rapport = "EMERGENCY SITE : "+ islandMap.getEmergencySiteId() + "\n CREEK : " + creek.getClosestID() ;
        } catch (InvalidMapException e) {
            rapport = "EMERGENCY SITE : "+ islandMap.getEmergencySiteId();
        }
        return rapport;
    }

}
