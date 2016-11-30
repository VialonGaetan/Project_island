package fr.unice.polytech.si3.qgl.iaad.result;

import com.sun.xml.internal.bind.v2.TODO;
import fr.unice.polytech.si3.qgl.iaad.Execption.InvalidIndexExecption;
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
    public String getBiome(int n) throws InvalidIndexExecption{
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.BIOMES.getName()).getString(n);
        }
        catch (Exception e){
            throw new InvalidIndexExecption(n,this.getClass().getSimpleName());
        }
    }

    @Override
    public int nbCreeks(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.CREEKS.getName()).length();
    }


    @Override
    public String getCreeks(int n) throws InvalidIndexExecption {
        try{
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.CREEKS.getName()).getString(0);
        }
        catch (Exception e){
            throw new InvalidIndexExecption(n,this.getClass().getSimpleName());
        }
    }

    @Override
    public String getSites() throws InvalidIndexExecption {
        try {
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.SITES.getName()).getString(0);
        }
        catch (Exception e){
            throw new InvalidIndexExecption(0,this.getClass().getSimpleName());
        }
    }


}
