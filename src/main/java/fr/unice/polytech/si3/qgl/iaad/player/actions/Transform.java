package fr.unice.polytech.si3.qgl.iaad.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Transform extends Ground{


    public Transform(Resource resource, Resource resource1, int nbResource, int nbResource1) {
        this.resource = resource;
        this.nbResource = nbResource;
        this.resource1 = resource1;
        this.nbResource1 =nbResource1;
        actionType = ArgActions.TRANSFORM;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action ECHO
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.TRANSFORM.getName()).put("parameters", new JSONObject().put(resource.getName(), nbResource).put(resource1.getName(), nbResource1));
    }

    @Override
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }

    @Override
    public String getKind(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getString(ArgResult.KIND.getName());
    }


    @Override
    public int getProduction(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getInt(ArgResult.PRODUCTION.getName());
    }
}