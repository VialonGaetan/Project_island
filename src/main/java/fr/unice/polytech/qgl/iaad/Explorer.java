package fr.unice.polytech.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.StringWriter;


public class Explorer implements IExplorerRaid {

    Context Initialisation;
    JSONObject Contract;
    JSONObject Result;

    @Override
    public void initialize(String s) {
        Contract = new JSONObject(s);
        Initialisation = new Context(Contract);
    }

    @Override
    public String takeDecision() {
        JSONObject actions = new JSONObject();
        actions.put("action","stop");
        return actions.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        Result = new JSONObject(s);

    }

    @Override
    public String deliverFinalReport() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
