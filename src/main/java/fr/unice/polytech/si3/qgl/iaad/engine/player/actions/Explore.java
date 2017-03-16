package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 *         Created the 15/11/2016.
 */
public class Explore implements Decision {

    private ArgActions actionType;
    public Explore() {
        actionType = ArgActions.EXPLORE;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action ECHO
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action", ArgActions.EXPLORE.getName());
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
