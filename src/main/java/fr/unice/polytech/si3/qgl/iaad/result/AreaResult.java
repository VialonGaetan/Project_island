package fr.unice.polytech.si3.qgl.iaad.result;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidIndexException;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidRequestException;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public abstract class AreaResult implements Results{
    String result;

    public AreaResult() {}

    public AreaResult(String result) {
        this.result = result;
    }

    // TODO: 30/11/2016 Throw execption à la place de null  
    /**
     * Recupere le cout d'une action
     * @return action cost
     */
    @Override
    public int getCost() {
        return new JSONObject(result).getInt(ArgResult.COST.getName());
    }

    @Override
    public String getStatus() {
        return new JSONObject(result).getString(ArgResult.STATUS.getName());
    }


    public String getFound()throws InvalidRequestException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }

    public int getRange() throws InvalidRequestException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }


    public int nbBiomes()throws InvalidRequestException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }

    public String getBiome(int n)throws InvalidRequestException, InvalidIndexException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }


    public String getCreeks(int n)throws InvalidRequestException, InvalidIndexException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }


    public int nbCreeks()throws InvalidRequestException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }


    public String getSites() throws InvalidRequestException, InvalidIndexException {
        throw new InvalidRequestException(this.getClass().getSimpleName());
    }


}
