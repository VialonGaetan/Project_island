package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Echo implements Decision{

    private Compass direction;
    private ArgActions actionType;

    public Echo(Compass direction) {
        this.direction = direction;
        actionType = ArgActions.ECHO;
        //results = new EchoResult();
    }


    @Override
    public ArgActions getActionEnum() {
        return actionType;
    }

    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action" , ArgActions.ECHO.getName()).put("parameters", new JSONObject().put("direction", direction.toString()));
    }
}
