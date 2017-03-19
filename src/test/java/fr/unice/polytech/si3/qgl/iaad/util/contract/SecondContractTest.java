package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Théo on 23/02/2017.
 */
public class SecondContractTest {

    Context context;
    SecondContract secondContract;
    private Contract contracts;
    Basket basket;
    Contract contract;

    @Before
    public void setUp() throws Exception
    {
        context = mock(Context.class);
        basket = new Basket();
        basket.put(Resource.FISH, 1000);
        basket.put(Resource.GLASS, 50);
        basket.put(Resource.WOOD, 100);
        basket.put(Resource.QUARTZ,100);
        contracts = new Contract(basket);


        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContract()).thenReturn(contracts);
        secondContract = new SecondContract(context, basket);
        this.secondContract.createSecondContract();
    }


    @Test
    public void createSecondContractTest(){
        Set listKeys=this.secondContract.getSecondContract().keySet();
        Iterator iterateur=listKeys.iterator();
        Resource tmpRes;
        for (int i=0;i<secondContract.getSecondContract().size();i++)
        {
            tmpRes=((Resource)(iterateur.next()));
           // assertTrue(tmpRes.isPrimary(tmpRes));
        }
        assertTrue(this.secondContract.getSecondContract().get(Resource.FISH)==1000);
        assertTrue(this.secondContract.getSecondContract().get(Resource.QUARTZ)==600);
    }

    @Test
    public void createSecondContractToBeCraftedTest(){
        Set listKeys=this.secondContract.getToBeCrafted().keySet();
        Iterator iterateur=listKeys.iterator();
        Resource tmpRes;

        while(iterateur.hasNext())
        {
            tmpRes=(Resource) iterateur.next();
            assertTrue(!(tmpRes).isPrimary());
        }
        assertTrue(this.secondContract.getToBeCrafted().get(Resource.GLASS)==50);
    }

    @Test
    public void isCraftableTest(){
        assertTrue(this.secondContract.isCraftable(Resource.GLASS,basket));
        assertFalse(this.secondContract.isCraftable(Resource.FISH,basket));
        assertTrue(this.secondContract.isCraftable(Resource.PLANK,basket));
        assertFalse(this.secondContract.isCraftable(Resource.RUM,basket));
        assertFalse(this.secondContract.isCraftable(Resource.WOOD,basket));
        assertTrue(this.secondContract.isCraftable(Resource.PLANK,basket));
    }


    @Test
    public void getCraftableResourceTest() {
    }
}

