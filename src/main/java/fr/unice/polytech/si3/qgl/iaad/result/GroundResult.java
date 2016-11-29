package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 16/11/2016.
 */

public abstract class GroundResult implements Results{

    public String result;

    public GroundResult(String result) {
        this.result = result;
    }

    /**
     * Recupere le cout d'une action
     * @return action cost
     */
    @Override
    public int getCost() {
        return new JSONObject(result).getInt(ArgResult.COST.getName());
    }

    @Override
    public String getStatus() {
        return new JSONObject(result).getString(ArgResult.STATUS.getName());
    }
}
