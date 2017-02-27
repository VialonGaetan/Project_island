package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Glimpse extends Ground{


    public Glimpse(Direction direction, int range) {
        this.direction=direction;
        this.range = range;
        actionType = ArgActions.GLIMPSE;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action Glimpse
     * @return JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.GLIMPSE.getName()).put("parameters", new JSONObject().put("direction", direction.toString()).put("range", range));
    }

    @Override
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int nbReport(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.REPORT.getName()).length();
    }

    @Override
    public String getBiomeReport(int inforange, int n){
        try{
            if (inforange<2) return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.REPORT.getName()).getJSONArray(inforange).getJSONArray(n).getString(0);
            else return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.REPORT.getName()).getJSONArray(inforange).getString(n);
        }
        catch (RuntimeException e){
            return null;
        }
    }

    /**
     * On ne l'utilise pas pour l'instant
     * @param inforange
     * @param n
     * @return
     */
    @Override
    public double getInfoReport(int inforange, int n){
        return -1;
    }

    @Override
    public int getDistanceResource(Resource resource){
        for (int i = 0; getBiomeReport(i,0) != null; i++) {
            for (int j = 0; getBiomeReport(i,j)!=null ; j++) {
                if(Biome.valueOf(getBiomeReport(i,j)).getAssociateResources().contains(resource)){
                    return i+1;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean biomeIsPresent(Biome biome){
        for (int i = 0; getBiomeReport(i,0) != null; i++) {
            for (int j = 0; getBiomeReport(i,j)!=null ; j++) {
                if(biome.equals(Biome.valueOf(getBiomeReport(i,j)))){
                    return true;
                }
            }
        }
        return false;
    }
}
