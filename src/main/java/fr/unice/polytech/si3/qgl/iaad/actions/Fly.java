package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Fly extends Area{

    public Fly() {
        actionType = ArgActions.FLY;
    }

    /**
     * Créé un objet FlyResult
     * @param result de l'explorer
     * @return FlyResult
     */
    @Override
    public Area putResults(String result) {
        this.result = result;
        return this;
    }


    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.FLY.getName());
    }
}
