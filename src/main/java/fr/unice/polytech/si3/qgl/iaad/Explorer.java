package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Actions.*;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.init.*;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.FlyResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;
import java.lang.reflect.*;
import org.json.JSONObject;


public class Explorer implements IExplorerRaid {

    private Context context;
    private String decision;
    private Action action;
    private Drone drone;
    private String rapport;

    @Override
    public void initialize(String s) {
        rapport = "";
        context = new Context(new JSONObject(s));
        drone = new Drone(this, context.getBudget(), Direction.valueOf(context.getHeading()));
    }

    @Override
    public String takeDecision() {
        //action = MakeDecision();
        return drone.doAction().toJSON();
    }

    @Override
    public void acknowledgeResults(String s) {
    }

    @Override
    public String deliverFinalReport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Results results(Action action){
        /*try{
            String typeAction = action.getClass().getName();
            Class c = Class.forName("result." + typeAction + "Result");
            Constructor constructor = c.getConstructor(String.class);
            Object o = constructor.newInstance(action);
            if(o instanceof Results)
                return (Results) o;
            else return null;
        }
        catch (Exception e){
            return null;
        }*/
        return null;
    }
}
