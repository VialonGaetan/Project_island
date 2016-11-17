package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * Created by user on 16/11/2016.
 */
public class ScoutResult extends GroundResult{

    public ScoutResult(String result) {
        super(result);
    }

    public int nbResources(){ return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).length();}

    public String getResource(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getString(n);
        }
        catch (RuntimeException e){
            return null;
        }
    }
}
