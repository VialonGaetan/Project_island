package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonArguments;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.json.JSONObject;

/**
 * @author Alexandre Clement
 * @since 18/03/2017.
 */
public class Oriented implements Decision
{
    private final ArgActions argActions;
    private Compass direction;

    Oriented(ArgActions argActions, Compass direction)
    {
        this.argActions = argActions;
        this.direction = direction;
    }

    public Compass getDirection()
    {
        return direction;
    }

    @Override
    public ArgActions getActionEnum()
    {
        return argActions;
    }

    @Override
    public JSONObject getJsonObject()
    {
        return new JSONObject()
                .put(JsonArguments.ACTION.toString(), argActions.getName())
                .put(JsonArguments.PARAMETERS.toString(), new JSONObject()
                        .put(JsonArguments.DIRECTION.toString(), direction.toString()));
    }
}
