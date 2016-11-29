package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Scout extends Ground {
    public Scout(Direction direction) {
        this.direction = direction;
    }

    /**
     * Créé un objet JSON avec l'action ECHO
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.SCOUT.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }
}
