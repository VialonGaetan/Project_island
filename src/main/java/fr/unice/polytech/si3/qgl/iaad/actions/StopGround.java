package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class StopGround extends Ground{

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action STOP
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.STOP.getName());
    }

    /**
     * Créé un objet FlyResult
     * @param result de l'explorer
     * @return FlyResult
     */
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }
}
