package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Move_to implements Decision {

    private Compass direction;
    private ArgActions actionType;

    public Move_to(Compass direction) {
        this.direction = direction;
        actionType = ArgActions.MOVE_TO;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action Move_to
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.MOVE_TO.getName()).put("parameters", new JSONObject().put("direction", direction.toString()));
    }

    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }
}
