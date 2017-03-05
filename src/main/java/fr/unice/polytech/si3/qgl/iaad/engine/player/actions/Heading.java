package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Heading extends Area{


    public Heading(Compass direction) {

        this.direction = direction;
        actionType = ArgActions.HEADING;
    }

    /**
     * Créé un objet HeadingResult
     * @param result de l'explorer
     * @return HeadingResult
     */
    @Override
    public Area putResults(String result) {
        this.result = result;
        return this;
    }

    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.HEADING.getName()).put("parameters", new JSONObject().put("direction", direction.toString()));
    }
}
