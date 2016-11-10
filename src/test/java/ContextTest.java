import fr.unice.polytech.qgl.iaad.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Gaetan VIALON on 10/11/2016.
 */
public class ContextTest {

    Context Contract;
    JSONObject FirstContrat;
    int NumContrat;

    @Before
    public void defineContext() {
        FirstContrat = new JSONObject("{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}");
        Contract = new Context(FirstContrat);
    }

    @Test
    public void TestHeading(){
        String Direction = "W";
        assertEquals(Direction, Contract.GetHeading());
    }

    @Test
    public void TestMen(){
        int Mens = 12;
        assertEquals(Mens, Contract.GetMens());
    }

    @Test
    public void TestBudget(){
        int Budget = 10000;
        assertEquals(Budget, Contract.GetBudget());
    }

    @Test
    public void TestNumberofContrats(){
        int Number = 2;
        assertEquals(Number, Contract.NbofContrats());
    }

    @Test
    public void TestAmountContrat1(){
        int Ammount = 600;
        NumContrat = 0;
        assertEquals(Ammount, Contract.ContratAmmount(NumContrat));
    }

    @Test
    public void TestRessourceContrat1(){
        String Ressource = "WOOD";
        NumContrat = 0;
        assertEquals(Ressource, Contract.ContratRessource(NumContrat));
    }

    @Test
    public void TestAmountContrat2(){
        int Ammount = 200;
        NumContrat = 1;
        assertEquals(Ammount, Contract.ContratAmmount(NumContrat));
    }

    @Test
    public void TestRessourceContrat2(){
        String Ressource = "GLASS";
        NumContrat = 1;
        assertEquals(Ressource, Contract.ContratRessource(NumContrat));
    }
}
