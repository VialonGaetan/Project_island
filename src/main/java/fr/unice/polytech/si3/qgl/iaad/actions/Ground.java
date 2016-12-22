package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */

public abstract class Ground implements Action, Results {

    Direction direction;
    int range, nbResource, nbResource1;
    Resource resource,resource1;
    String result;

    public abstract Ground putResults(String result);

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

    public String getResource(int n){
        return null;
    }

    public int nbResources(){
        return -1;
    }

    public String getKind(){return null;}

    public int getProduction(){return -1;}

    public int getAmountExploit(){return -1;}

    public int nbResourceExplore(){return -1;}

    public String getAmountExplore(int n) {return null;}

    public String getRessourceExplore(int n) {return null;}

    public String getCondExplore(int n) {return null;}

    public String getPoisExplore() {return null;}

    public int getAltitude(){return -1;}

    public int getRange(){return -1;}

    public int nbReport(){return -1;}

    public int getInfoReport(int n){ return -1;}

    public String getResourceReport(int n){return null;}

    public String getRessourceExploit(){return null;}
}
