package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class ScanResult extends AreaResult {

    public ScanResult(String result) {
        super(result);
    }

    @Override
    public int nbBiomes(){ return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.BIOMES.getName()).length();}

    @Override
    public String getBiome(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.BIOMES.getName()).getString(n);
        }
        catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public String getCreek(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.CREEK.getName()).getString(0);
    }

    @Override
    public String getSites(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.SITES.getName()).getString(0);
    }


}
