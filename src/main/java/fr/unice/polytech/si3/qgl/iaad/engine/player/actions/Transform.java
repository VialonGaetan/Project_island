package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonArguments;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Transform implements Decision{

    private final Manufactured manufactured;
    private final int quantity;
    private ArgActions actionType;

    public Transform(Manufactured manufactured, int quantity)
    {
        this.manufactured = manufactured;
        this.quantity = quantity;
        actionType = ArgActions.TRANSFORM;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action ECHO
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        JSONObject parameters = new JSONObject();
        for (Map.Entry<PrimaryResource, Double> entry : manufactured.getRecipe().entrySet())
        {
            parameters.put(entry.getKey().toString(), (int) Math.ceil(entry.getValue() * quantity));
        }
        return new JSONObject().put(JsonArguments.ACTION.toString(), ArgActions.TRANSFORM.getName())
                .put(JsonArguments.PARAMETERS.toString(), parameters);
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
