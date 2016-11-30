package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class EchoResult extends AreaResult{

    public EchoResult() {}

    public EchoResult(String result) {
        super(result);
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
