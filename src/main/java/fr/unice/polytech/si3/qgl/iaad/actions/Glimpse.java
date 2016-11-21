package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Glimpse extends Ground{
    public Glimpse(Direction direction, int range) {
        this.direction=direction;
        this.range = range;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.GLIMPSE.getName()).put("parameters", new JSONObject().put("direction", direction.toString()).put("range", range)).toString();
    }

}
