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
    }

    /**
     * Créé un objet JSON avec l'action Land
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.LAND.getName()).put("parameters", new JSONObject().put("creek", ID.toString()).put("people", people)).toString();
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
