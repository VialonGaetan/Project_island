package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Scan extends Area {


    public Scan() {}

    /**
     * Créé un objet JSON avec l'action SCAN
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.SCAN.getName()).toString();
    }

    /**
     * Créé un objet ScanResult
     * @param result de l'explorer
     * @return ScanResult
     */
    @Override
    public Area putResults(String result) {
        this.result = result;
        return this;
    }

    @Override
    public int nbBiomes(){ return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.BIOMES.getName()).length();}

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
