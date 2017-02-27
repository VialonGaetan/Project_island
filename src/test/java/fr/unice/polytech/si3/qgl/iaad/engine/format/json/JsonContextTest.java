package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.StandardContract;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class JsonContextTest
{
    private JSONObject jsonObject;
    private JsonContext context;

    @Before
    public void setUp() throws Exception
    {
        jsonObject = new JSONObject()
                .put("men", 12)
                .put("budget", 10000)
                .put("contracts",new JSONArray()
                        .put(new JSONObject()
                                .put("amount", 600)
                                .put("resource", "WOOD"))
                        .put(new JSONObject()
                                .put("amount", 200)
                                .put("resource", "GLASS")))
                .put("heading", "W");
        context = new JsonContext(jsonObject);
    }

    @Test
    public void getNumberOfMen() throws Exception
    {
        assertEquals(12, context.getNumberOfMen());
    }

    @Test
    public void getBudget() throws Exception
    {
        assertEquals(10000, context.getBudget());
    }

    @Test
    public void getContracts() throws Exception
    {
        List<Contract> expected = new ArrayList<>();
        expected.add(new StandardContract(Resource.WOOD, 600));
        expected.add(new StandardContract(Resource.GLASS, 200));
        assertEquals(expected, context.getContracts());
    }

    @Test
    public void getHeading() throws Exception
    {
        assertEquals(Direction.W, context.getHeading());
    }


}