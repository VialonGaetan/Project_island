package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Echo extends Area {


    public Echo(Direction direction) {
        this.direction = direction;
        results = new EchoResult();
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.ECHO.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }

    @Override
    public AreaResult getResults(String result) {
        return new EchoResult(result);
    }
}
