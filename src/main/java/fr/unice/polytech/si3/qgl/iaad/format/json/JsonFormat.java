package fr.unice.polytech.si3.qgl.iaad.format.json;

import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.format.Format;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.future.Decision;
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
