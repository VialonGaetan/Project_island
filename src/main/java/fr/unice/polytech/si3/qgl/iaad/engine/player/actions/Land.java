package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Land implements Decision {

    private String ID;
    private int people;
    private ArgActions actionType;

    public Land(String ID, int people) {
        this.ID = ID;
        this.people=people;
        actionType = ArgActions.LAND;
    }

    public String getID() {
        return ID;
    }

    public int getPeople() {
        return people;
    }

    public ArgActions getActionType() {
        return actionType;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action Land
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.LAND.getName()).put("parameters", new JSONObject().put("creek", ID.toString()).put("people", people));
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
