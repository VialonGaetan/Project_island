package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Move_to extends Ground {
    public Move_to(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.MOVE_TO.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }
}
