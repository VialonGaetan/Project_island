package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Théo on 23/02/2017.
 */
public class SecondContractTest {

    Context context;
    JSONObject firstContext;
    SecondContract secondContract;
    private List<Contract> contracts;

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
        //context = new Context(firstContext);

    }

    @Ignore
    @Before
    public void setUp() throws Exception
    {
        context = mock(Context.class);
        contracts = new ArrayList<>();
        //contracts.add(new Contract(Resource.FISH, 1000));
        //contracts.add(new StandardContract(Resource.GLASS, 50));

        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Direction.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContracts()).thenReturn(contracts);
        secondContract = new SecondContract(context);
        this.secondContract.createSecondContract();
    }

    @Ignore
    @Test
    public void createSecondContractTest(){
        Set listKeys=this.secondContract.getSecondContract().keySet();
        Iterator iterateur=listKeys.iterator();
        while(iterateur.hasNext())
        {
            assertTrue(Resource.isPrimary((Resource) iterateur.next()));
        }
        assertTrue(this.secondContract.getSecondContract().get(Resource.WOOD)==1600);
        assertTrue(this.secondContract.getSecondContract().get(Resource.QUARTZ)==2000);
    }

    @Ignore
    @Test
    public void createSecondContractToBeCraftedTest(){
        Set listKeys=this.secondContract.getToBeCrafted().keySet();
        Iterator iterateur=listKeys.iterator();
        while(iterateur.hasNext())
        {
            assertTrue(!Resource.isPrimary((Resource) iterateur.next()));
        }
        assertTrue(this.secondContract.getToBeCrafted().get(Resource.GLASS)==200);
    }
}

