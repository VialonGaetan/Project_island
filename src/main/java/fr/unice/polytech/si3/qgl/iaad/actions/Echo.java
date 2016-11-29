package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Echo extends Area {


    public Echo(Direction direction) {
        this.direction = direction;
        results = new EchoResult();
    }

    /**
     * Créé un objet JSON avec l'action ECHO
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.ECHO.getName()).put("parameters", new JSONObject().put("direction", direction.toString())).toString();
    }

    /**
     * Créé un objet EchoResult
     * @param result de l'explorer
     * @return EchoResult
     */
    @Override
    public AreaResult getResults(String result) {
        return new EchoResult(result);
    }
}
