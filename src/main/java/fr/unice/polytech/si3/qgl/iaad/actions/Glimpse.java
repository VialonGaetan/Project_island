package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Glimpse extends Ground{
    public Glimpse(Direction direction, int range) {
        this.direction=direction;
        this.range = range;
    }

    /**
     * Créé un objet JSON avec l'action Glimpse
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.GLIMPSE.getName()).put("parameters", new JSONObject().put("direction", direction.toString()).put("range", range)).toString();
    }

}
