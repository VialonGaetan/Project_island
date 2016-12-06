package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public abstract class AreaResult implements Results{
    String result;

    public AreaResult() {}

    public AreaResult(String result) {
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


    public String getFound(){
        return null;
    }

    public int getRange(){
        return -1;
    }


    public int nbBiomes(){
        return -1;
    }

    public String getBiome(int n){
        return null;
    }


    public String getCreeks(int n) {
        return null;
    }


    public int nbCreeks(){
        return -1;
    }


    public String getSites(){
        return null;
    }


}
