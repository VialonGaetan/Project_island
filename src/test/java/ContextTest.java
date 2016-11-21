import fr.unice.polytech.si3.qgl.iaad.init.Context;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gaetan VIALON on 10/11/2016.
 */
public class ContextTest {

    Context context;
    JSONObject firstContext;
    int numContrat;

    // TODO: 21/11/2016 Rajouter des tests 
    @Before
    public void defineContext() {

        firstContext = new JSONObject("{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}");
        context = new Context(firstContext);
    }

    @Test
    public void testHeading(){
        String Direction = "W";
        assertEquals(Direction, context.getHeading());
    }

    @Test
    public void testMen(){
        int mens = 12;
        assertEquals(mens, context.getMen());
    }

    @Test
    public void testBudget(){
        int budget = 10000;
        assertEquals(budget, context.getBudget());
    }

    @Test
    public void testNumberOfContracts(){
        int number = 2;
        assertEquals(number, context.numberOfContrats());
    }

    @Test
    public void testAmountContract1(){
        int ammount = 600;
        numContrat = 0;
        assertEquals(ammount, context.getContract(numContrat).getAmount());
    }

    @Test
    public void testRessourceContrat1(){
        String Ressource = "WOOD";
        numContrat = 0;
        assertEquals(Ressource, context.getContract(numContrat).getResource());
    }

    @Test
    public void testAmountContrat2(){
        int ammount = 200;
        numContrat = 1;
        assertEquals(ammount, context.getContract(numContrat).getAmount());
    }

    @Test
    public void testRessourceContract2(){
        String ressource = "GLASS";
        numContrat = 1;
        assertEquals(ressource, context.getContract(numContrat).getResource());
    }
}
