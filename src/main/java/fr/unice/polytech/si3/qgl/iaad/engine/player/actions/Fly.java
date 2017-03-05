package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Fly implements Decision{

    private ArgActions actionType;

    public Fly() {
        actionType = ArgActions.FLY;
    }

    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.FLY.getName());
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
