package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Land extends Area {


    public Land(String ID, int people) {
        this.ID = ID;
        this.people=people;
        actionType = ArgActions.LAND;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action Land
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.LAND.getName()).put("parameters", new JSONObject().put("creek", ID.toString()).put("people", people));
    }

    /**
     * Créé un objet LandResult
     * @param result de l'explorer
     * @return LandResult
     */
    @Override
    public Area putResults(String result) {
        this.result = result;
        return this;
    }
}
