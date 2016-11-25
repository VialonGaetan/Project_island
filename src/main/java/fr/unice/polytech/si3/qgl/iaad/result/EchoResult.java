package fr.unice.polytech.si3.qgl.iaad.result;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class EchoResult extends AreaResult{

    public EchoResult() {}

    public EchoResult(String result) {
        super(result);
    }



    @Override
    public String getFound(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getString(ArgResult.FOUND.getName());
    }

    @Override
    public int getRange(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getInt(ArgResult.RANGE.getName());
    }

}
