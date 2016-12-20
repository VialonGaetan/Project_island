package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Fly extends Area{

    public Fly() {
    }

    /**
     * Créé un objet JSON avec l'action Fly
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.FLY.getName()).toString();
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
}
