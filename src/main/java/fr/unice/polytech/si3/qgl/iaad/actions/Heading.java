package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.FlyResult;
import fr.unice.polytech.si3.qgl.iaad.result.HeadingResult;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Heading extends Area{

    public Heading(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.HEADING.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }

    @Override
    public AreaResult getResults(String result) {
        //results.putResult(result);
        return new HeadingResult(result);
    }
}
