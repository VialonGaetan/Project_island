package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * Created by user on 16/11/2016.
 */
public abstract class GroundResult implements Results{

    String result;

    public GroundResult(String result) {
        this.result = result;
    }

    @Override
    public int getCost() {
        return new JSONObject(result).getInt(ArgResult.COST.getName());
    }

    @Override
    public String getStatus() {
        return new JSONObject(result).getString(ArgResult.STATUS.getName());
    }
}