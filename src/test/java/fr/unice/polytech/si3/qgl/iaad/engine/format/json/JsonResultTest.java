package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandre Clement
 * @since 22/02/2017.
 */
public class JsonResultTest
{
    @Test
    public void noCost() throws Exception
    {
        JSONObject jsonObject = new JSONObject().put("cost", 0).put("extras", new JSONObject());
        Result result = new JsonResult(jsonObject);
        assertEquals(0, result.getCost());
    }

    @Test
    public void normalCost() throws Exception
    {
        JSONObject jsonObject = new JSONObject().put("cost", 30).put("extras", new JSONObject());
        Result result = new JsonResult(jsonObject);
        assertEquals(30, result.getCost());
    }
}