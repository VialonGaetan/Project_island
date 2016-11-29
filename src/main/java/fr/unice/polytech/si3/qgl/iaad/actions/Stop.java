package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Stop extends Area {

    public Stop() {}

    public Stop(String rapport) {
        super.rapport = rapport;
    }

    /**
     * Créé un objet JSON avec l'action STOP
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.STOP.getName()).toString();
    }

    /**
     * Créé un objet FlyResult
     * @param result de l'explorer
     * @return FlyResult
     */
    @Override
    public AreaResult getResults(String result) {
        return new StopResult(result);
    }
}
