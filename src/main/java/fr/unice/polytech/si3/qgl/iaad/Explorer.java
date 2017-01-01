package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
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
    public static boolean areaPhase = true;
    private Area areaAction;
    private Ground groundAction;
    private String rapport;
    private Creek creek;
    private NaiveLanding naive;
    private String resultat;

    @Override
    public void initialize(String s) {
        islandMap = new IslandMap();
        context = new Context(new JSONObject(s));
        budget = context.getBudget();
        drone = new Drone(budget, Direction.valueOf(context.getHeading()), islandMap);
        naive = new NaiveLanding(context,context.getContract(0),islandMap);
        groundAction = new Explore();
    }

    @Override
    public String takeDecision() {
        if(areaPhase){
            areaAction = (Area) drone.doAction();
            decision = areaAction.toJSON();
        }
        else{
            try {
                naive = new NaiveLanding(context,context.getContract(0),islandMap);
                groundAction = (Ground) naive.nextAction((groundAction).putResults(resultat));
            } catch (InvalidMapException e) {
            }
            decision = groundAction.toJSON();
        }
        return decision;
    }

    /**
     * decision taken
     */
    @Override
    public void acknowledgeResults(String s) {
        try {
            if(areaPhase){
                drone.getResult((areaAction).putResults(s));
                resultat = s;
            }
            else{
                resultat = s;
            }
        } catch (InvalidMapException exception) {
            // according to map designer, it's ok
        }
    }

    /**
     * result
     */
    @Override
    public String deliverFinalReport() {
        if (false) {
            try {
                creek = new Creek(islandMap);
                rapport = "EMERGENCY:" + islandMap.getEmergencySiteId() + "\nCREEK:" + creek.getClosestID(creek.getClosest(islandMap));
            } catch (InvalidMapException | ArrayIndexOutOfBoundsException e) {
                rapport = "EMERGENCY:" + islandMap.getEmergencySiteId();
            }
        } else rapport = "Nous allons être riches !! ";
        return rapport;
    }

}