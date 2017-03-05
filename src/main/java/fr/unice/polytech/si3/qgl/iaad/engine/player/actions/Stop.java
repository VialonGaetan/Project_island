package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Stop implements Decision{

    private ArgActions actionType;

    public Stop() {
        actionType = ArgActions.STOP;
    }

    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.STOP.getName());
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
