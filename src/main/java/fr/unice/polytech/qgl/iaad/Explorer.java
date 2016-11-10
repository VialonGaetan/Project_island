package fr.unice.polytech.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.StringWriter;


public class Explorer implements IExplorerRaid {

    int nbSpace = 1;
    Context Initialisation;
    @Override
    public void initialize(String s) {
        JSONObject Contract = new JSONObject(s);
        Initialisation = new Context(Contract);
    }

    @Override
    public String takeDecision() {
        JSONObject actions = new JSONObject();
        actions.put("action","stop");

        return actions.toString(nbSpace);


    }

    @Override
    public void acknowledgeResults(String s) {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public String deliverFinalReport() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
