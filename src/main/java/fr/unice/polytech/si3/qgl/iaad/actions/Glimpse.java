package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Biomes;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Glimpse extends Ground{
    public Glimpse(Direction direction, int range) {
        this.direction=direction;
        this.range = range;
    }

    /**
     * Créé un objet JSON avec l'action Glimpse
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.GLIMPSE.getName()).put("parameters", new JSONObject().put("direction", direction.toString()).put("range", range)).toString();
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
    public String getResourceReport(int inforange, int n){
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
        for (int i = 0; getResourceReport(i,0) != null; i++) {
            for (int j = 0; getResourceReport(i,j)!=null ; j++) {
                if(Biomes.valueOf(getResourceReport(i,j)).getAssociateResources().contains(resource)){
                    return i+1;
                }
            }
        }
        return -1;
    }

}
