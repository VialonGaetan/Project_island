package fr.unice.polytech.qgl.iaad;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.StringWriter;


public class Explorer implements IExplorerRaid {

    private Context Initialisation;
    private AnswersQuery Answer;
    private JSONObject Contract;
    private JSONObject Result;
    private JSONObject RequestActions;
    String Action;


    @Override
    public void initialize(String s) {
        Contract = new JSONObject(s);
        Initialisation = new Context(Contract);
    }

    @Override
    public String takeDecision() {
        RequestActions = new JSONObject();
        Action = "stop";
        RequestActions.put("action",Action);
        return RequestActions.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        Result = new JSONObject(s);
        Answer = new AnswersQuery(Result);
    }

    @Override
    public String deliverFinalReport() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
