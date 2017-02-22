package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.ground.Exploration;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.board.OldCreek;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;
import org.json.JSONObject;

public class Explorer implements IExplorerRaid {

    private int budget;
    private IslandMap islandMap;
    private Context context;
    private String decision;
    private Drone drone;
    private Exploration exploration;
    public static boolean areaPhase;
    private Area areaAction;
    private Ground groundAction;
    private String rapport;
    private OldCreek oldCreek;


    @Override
    public void initialize(String s) {
        islandMap = new IslandMap();
        context = new Context(new JSONObject(s));
        budget = context.getBudget();
        drone = new Drone(budget, Direction.valueOf(context.getHeading()), islandMap);
        areaPhase = true;

    }

    @Override
    public String takeDecision() {
        if(!areaPhase){
            groundAction = (Ground) exploration.doAction();
            decision = groundAction.getJsonObject().toString();
        }
        if(areaPhase){
            areaAction = (Area) drone.doAction();
            decision = areaAction.getJsonObject().toString();
        }
        return decision;
    }

    /**
     * decision taken
     */
    @Override
    public void acknowledgeResults(String s) {
        try {
            if(!areaPhase){
                exploration.getResult((groundAction).putResults(s));
            }
            if(areaPhase){
                drone.getResult((areaAction).putResults(s));
            }
            if(areaAction instanceof Land && areaPhase) {
                areaPhase = false;
                exploration = new Exploration(drone.getBudget(),islandMap,context);
            }
        } catch (InvalidMapException exception) {
            // according to board designer, it's ok
        }
    }

    /**
     * fr.unice.polytech.si3.qgl.iaad.result
     */
    @Override
    public String deliverFinalReport() {
        if (false) {
            try {
                oldCreek = new OldCreek(islandMap);
                rapport = "EMERGENCY:" + islandMap.getEmergencySiteId() + "\nCREEK:" + oldCreek.getClosestCreekId();
            } catch (InvalidMapException | ArrayIndexOutOfBoundsException e) {
                rapport = "EMERGENCY:" + islandMap.getEmergencySiteId();
            }
        } else rapport = "Nous allons être riches !!  ";
        return rapport;
    }

}