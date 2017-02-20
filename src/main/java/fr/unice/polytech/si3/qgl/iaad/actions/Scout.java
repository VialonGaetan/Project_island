package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Scout extends Ground {


    public Scout(Direction direction) {
        this.direction = direction;
        actionType = ArgActions.SCOUT;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action ECHO
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.SCOUT.getName()).put("parameters", new JSONObject().put("direction", direction.toString()));
    }

    @Override
    public int nbResources(){ return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).length();}

    @Override
    public String getResource(int n){
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getString(n);
        }
        catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public int getAltitude(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getInt(ArgResult.ALTITUDE.getName());
    }

    @Override
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }
}
