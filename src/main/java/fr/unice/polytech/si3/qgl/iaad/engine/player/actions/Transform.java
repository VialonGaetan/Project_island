package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Transform implements Decision{

    private ArgActions actionType;
    private Resource resource, resource1;
    private int nbResource, nbResource1;

    public Transform(Resource resource, int nbResource,Resource resource1, int nbResource1) {
        this.resource = resource;
        this.nbResource = nbResource;
        this.resource1 = resource1;
        this.nbResource1 =nbResource1;
        actionType = ArgActions.TRANSFORM;
    }

    public Transform(Resource resource, int nbResource) {
        this.resource = resource;
        this.nbResource = nbResource;
        actionType = ArgActions.TRANSFORM;
    }



    /**
     * Créé un objet JOSN avec l'fr.unice.polytech.si3.qgl.iaad.action ECHO
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject()
    {
        if(nbResource1 == 0)
        {
            return new JSONObject().put("action",ArgActions.TRANSFORM.getName()).put("parameters",new JSONObject().put(resource.getName(),nbResource));
        }

        return new JSONObject().put("action" , ArgActions.TRANSFORM.getName()).put("parameters", new JSONObject().put(resource.getName(), nbResource).put(resource1.getName(), nbResource1));
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
