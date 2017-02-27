package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Format;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;
import org.json.JSONObject;

/**
 * @author Alexandre Clement
 * @since 05/02/2017.
 */
public class JsonFormat implements Format
{
    @Override
    public Context stringToContext(String initialization)
    {
        return new JsonContext(new JSONObject(initialization));
    }

    @Override
    public String decisionToString(Decision decision)
    {
        return decision.getJsonObject().toString();
    }

    @Override
    public Result stringToResult(String result)
    {
        return new JsonResult(new JSONObject(result));
    }
}
