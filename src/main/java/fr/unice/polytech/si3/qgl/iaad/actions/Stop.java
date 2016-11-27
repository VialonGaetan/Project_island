package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.HeadingResult;
import fr.unice.polytech.si3.qgl.iaad.result.StopResult;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Stop extends Area {

    private String rapport;

    public Stop() {}

    public Stop(String rapport) {
        super.rapport = rapport;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.STOP.getName()).toString();
    }
    @Override
    public AreaResult getResults(String result) {
        //results.putResult(result);
        return new StopResult(result);
    }
}
