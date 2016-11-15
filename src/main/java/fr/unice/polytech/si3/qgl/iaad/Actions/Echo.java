package fr.unice.polytech.si3.qgl.iaad.Actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Echo extends Area {


    public Echo(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.ECHO.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }
}
