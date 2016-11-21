package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Land extends Common {
    public Land(String ID, int people) {
        this.ID = ID;
        this.people=people;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.LAND.getName()).put("parameters", new JSONObject().put("creek", ID.toString()).put("people", people)).toString();
    }
}
