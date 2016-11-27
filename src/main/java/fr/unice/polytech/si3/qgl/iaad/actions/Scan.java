package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.FlyResult;
import fr.unice.polytech.si3.qgl.iaad.result.HeadingResult;
import fr.unice.polytech.si3.qgl.iaad.result.ScanResult;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Scan extends Area {


    public Scan() {
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.SCAN.getName()).toString();
    }

    @Override
    public AreaResult getResults(String result) {
        //results.putResult(result);
        return new ScanResult(result);
    }
}
