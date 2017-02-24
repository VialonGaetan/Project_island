package fr.unice.polytech.si3.qgl.iaad.contract;

import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Théo on 23/02/2017.
 */
public class SecondContractTest {

    Context context;
    JSONObject firstContext;
    SecondContract secondContract;

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
        secondContract = new SecondContract(context);
        this.secondContract.createSecondContract();
    }

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

