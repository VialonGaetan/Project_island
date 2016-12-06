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

    // TODO: 30/11/2016 Faire des execption
    @Override
    public String getBiome(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.BIOMES.getName()).getString(n);
        }catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public int nbCreeks(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.CREEKS.getName()).length();
    }


    @Override
    public String getCreeks(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.CREEKS.getName()).getString(0);
        }catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public String getSites(){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.SITES.getName()).getString(0);
        }catch (RuntimeException e){
            return null;
        }
    }


}
