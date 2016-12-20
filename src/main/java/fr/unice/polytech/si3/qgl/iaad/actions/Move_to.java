package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Move_to extends Ground {
    public Move_to(Direction direction) {
        this.direction = direction;
    }

    /**
     * Créé un objet JSON avec l'action Move_to
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.MOVE_TO.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }

    @Override
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }
}
