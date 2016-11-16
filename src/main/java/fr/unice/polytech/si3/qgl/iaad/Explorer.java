package fr.unice.polytech.si3.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.Actions.*;
import fr.unice.polytech.si3.qgl.iaad.init.*;
import org.json.JSONObject;


public class Explorer implements IExplorerRaid {

    private Context context;
    private ResultActions resultActions;


    @Override
    public void initialize(String s) {
        context = new Context(new JSONObject(s));
    }

    @Override
    public String takeDecision() {
        return new Fly().toJSON();
    }

    @Override
    public void acknowledgeResults(String s) { resultActions = new ResultActions(new JSONObject(s));}

    @Override
    public String deliverFinalReport() {
        throw new UnsupportedOperationException("not implemented yet.");
    }
}
