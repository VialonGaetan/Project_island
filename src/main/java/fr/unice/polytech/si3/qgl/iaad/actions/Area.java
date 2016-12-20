package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */

public abstract class Area implements Action, Results {

    public Direction direction;
    public String result;
    public String ID;
    public int people;

    /**
     * Lorsqu'une action est effectué créé un objet Result correspondant à l'action
     * @param result de l'explorer
     * @return objet resultat avec les données
     */
    public abstract Area putResults(String result);

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


    public String getFound(){
        return null;
    }

    public int getRange(){
        return -1;
    }


    public int nbBiomes(){
        return -1;
    }

    public String getBiome(int n){
        return null;
    }


    public String getCreeks(int n) {
        return null;
    }


    public int nbCreeks(){
        return -1;
    }


    public String getSites(){
        return null;
    }
}
