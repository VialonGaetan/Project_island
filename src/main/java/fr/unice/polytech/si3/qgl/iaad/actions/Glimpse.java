package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
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
    public int getRange(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getInt(ArgResult.ASKED_RANGE.getName());
    }

    @Override
    public int nbReport(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.REPORT.getName()).length();
    }

    @Override
    public String getResourceReport(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.REPORT.getName()).getString(n);
        }
        catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public int getInfoReport(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.REPORT.getName()).getInt(n);
        }
        catch (RuntimeException e){
            return -1;
        }
    }

}
