package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Echo extends Area {


    public Echo(Compass direction) {
        this.direction = direction;
        actionType = ArgActions.ECHO;
        //results = new EchoResult();
    }


    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.ECHO.getName()).put("parameters", new JSONObject().put("direction", direction.toString()));
    }
    
    /**
     * Créé un objet EchoResult
     * @param result de l'explorer
     * @return EchoResult
     */
    @Override
    public Area putResults(String result) {
        this.result = result;
        return this;
    }

    /**
     * Permet de recuperer la reponse d'un echo
     * @return out_of_bounds or ground
     */
    @Override
    public String getFound(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getString(ArgResult.FOUND.getName());
    }

    /**
     * Permet de recuperer la distance d'un echo
     * @return range
     */
    @Override
    public int getRange(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getInt(ArgResult.RANGE.getName());
    }
}
