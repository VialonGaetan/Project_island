package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.FlyResult;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Fly extends Area{

    public Fly() {
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.FLY.getName()).toString();
    }

    @Override
    public AreaResult getResults(String result) {
        //results.putResult(result);
        return new FlyResult(result);
    }
}
