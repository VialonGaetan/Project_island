package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Before;
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
    SecondContract secondContract;
    private List<Contract> contracts;

    @Before
    public void setUp() throws Exception
    {
        context = mock(Context.class);
        contracts = new ArrayList<>();
        contracts.add(new Contract(Resource.FISH, 1000));
        contracts.add(new Contract(Resource.GLASS, 50));
        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContracts()).thenReturn(contracts);
        secondContract = new SecondContract(context);
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
            assertTrue(tmpRes.isPrimary(tmpRes));
        }
        assertTrue(this.secondContract.getSecondContract().get(Resource.FISH)==1000);
        assertTrue(this.secondContract.getSecondContract().get(Resource.QUARTZ)==500);
    }

    @Test
    public void createSecondContractToBeCraftedTest(){
        Set listKeys=this.secondContract.getToBeCrafted().keySet();
        Iterator iterateur=listKeys.iterator();
        Resource tmpRes;

        while(iterateur.hasNext())
        {
            tmpRes=(Resource) iterateur.next();
            assertTrue(!(tmpRes).isPrimary(tmpRes));
        }
        assertTrue(this.secondContract.getToBeCrafted().get(Resource.GLASS)==50);
    }
}

