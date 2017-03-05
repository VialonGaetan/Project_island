package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */

public abstract class Area implements Results, Decision {

    public Compass direction;
    public String result;
    public String ID;
    public int people;
    ArgActions actionType;

    /**
     * Lorsqu'une fr.unice.polytech.si3.qgl.iaad.action est effectué créé un objet Result correspondant à l'fr.unice.polytech.si3.qgl.iaad.action
     * @param result de l'explorer
     * @return objet resultat avec les données
     */
    public abstract Area putResults(String result);

    /**
     * Recupere le cout d'une fr.unice.polytech.si3.qgl.iaad.action
     * @return fr.unice.polytech.si3.qgl.iaad.action cost
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

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
