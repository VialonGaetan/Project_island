package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Scan implements Decision {


    private ArgActions actionType;

    public Scan() {
        actionType = ArgActions.SCAN;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action SCAN
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.SCAN.getName());
    }

    /**
     * Créé un objet ScanResult
     * @param result de l'explorer
     * @return ScanResult
     */
    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
