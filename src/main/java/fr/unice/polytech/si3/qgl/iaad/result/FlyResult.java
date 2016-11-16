package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class FlyResult extends AreaResult {

    public FlyResult(String result) {
        this.result = result;
    }

    @Override
    public int getCost() {
        return new JSONObject(result).getInt("cost");
    }

    @Override
    public String getStatus() {
        return new JSONObject(result).getString("Status");
    }

}
