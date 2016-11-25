package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public abstract class AreaResult implements Results{
    String result;

    public AreaResult() {}

    public AreaResult(String result) {
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

    public String getFound(){return null;}

    public int getRange(){return -1;}

    public int nbBiomes(){ return -1;}

    public String getBiome(int n){return null;}

    public String getCreek(){return null;}

    public String getSites(){return null;}

    //@Override
    public void putResult(String result) {
        this.result = result;
    }
}
