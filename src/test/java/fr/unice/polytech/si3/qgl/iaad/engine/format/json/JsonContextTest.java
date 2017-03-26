package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

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
        assertEquals(new Budget(10000), context.getBudget());
    }

    @Test
    public void getContracts() throws Exception
    {
        Contract contract = new Contract();
        contract.addContract(new PrimaryContract(PrimaryResource.QUARTZ,2000));
        contract.addContract(new PrimaryContract(PrimaryResource.WOOD,1600));
        assertEquals(contract.getTotalBasket().entrySet(), context.getContract().getTotalBasket().entrySet());
    }

    @Test
    public void getHeading() throws Exception
    {
        assertEquals(Compass.W, context.getHeading());
    }


}