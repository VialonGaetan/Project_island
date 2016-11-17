package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Actions.*;
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
    private Results resultsAction = new FlyResult("lol");

    @Override
    public void initialize(String s) {
        context = new Context(new JSONObject(s));
    }

    @Override
    public String takeDecision() {
        //action = MakeDecision();
        action = new Fly();
        decision = action.toJSON();
        return decision;
    }

    @Override
    public void acknowledgeResults(String s) {

    }

    @Override
    public String deliverFinalReport() {
        throw new UnsupportedOperationException("not implemented yet.");
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
