package fr.unice.polytech.si3.qgl.iaad.Actions;

import fr.unice.polytech.si3.qgl.iaad.Resource;
import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Transform extends Ground{
    public Transform(Resource resource, Resource resource1, int nbResource, int nbResource1) {
        this.resource = resource;
        this.nbResource = nbResource;
        this.resource1 = resource1;
        this.nbResource1 =nbResource1;
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.TRANSFORM.getName()).put("parameters", new JSONObject().put(resource.getName(), nbResource).put(resource1.getName(), nbResource1)).toString();
    }
}
