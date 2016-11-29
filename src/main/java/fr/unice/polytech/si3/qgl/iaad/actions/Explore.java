package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Explore extends Ground {

    public Explore() {}

    /**
     * Créé un objet JSON avec l'action ECHO
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.EXPLORE.getName()).toString();
    }
}
