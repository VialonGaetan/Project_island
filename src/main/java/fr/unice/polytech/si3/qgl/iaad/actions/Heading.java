package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.HeadingResult;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Heading extends Area{

    public Heading(Direction direction) {
        this.direction = direction;
    }

    /**
     * Créé un objet JSON avec l'action ECHO
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.HEADING.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }

    /**
     * Créé un objet HeadingResult
     * @param result de l'explorer
     * @return HeadingResult
     */
    @Override
    public AreaResult getResults(String result) {
        return new HeadingResult(result);
    }
}
