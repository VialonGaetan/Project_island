package fr.unice.polytech.qgl.iaad;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Alexandre Clement
 *         Created the 12/11/2016.
 */
public class Decision {

    private JSONObject decision = new JSONObject();
    private JSONObject parameters = new JSONObject();

    public Decision putAction(Action action) {
        decision.put(Request.ACTION.getName(), action.getName());
        return this;
    }

    public Action getAction() {
        String actionDecision = decision.getString(Request.ACTION.getName());
        for (Action action: Action.values())
            if (action.getName().equals(actionDecision))
                return action;
        return null;
    }

    public Decision putParameters(Direction direction) {
        parameters.put(Request.DIRECTION.getName(), direction);
        return this;
    }

    public String getDecision() {
        if (parameters.length() > 0)
            decision.put(Request.PARAMETERS.getName(), parameters);
        return decision.toString();
    }
}

enum Request {
    ACTION("action"),
    PARAMETERS("parameters"),
    DIRECTION("direction");

    private String name;

    public String getName() {
        return name;
    }

    Request(String name) {
        this.name = name;
    }
}
