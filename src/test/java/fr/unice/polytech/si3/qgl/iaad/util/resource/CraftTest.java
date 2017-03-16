package fr.unice.polytech.si3.qgl.iaad.util.resource;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.SecondContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Théo on 22/02/2017.
 */
public class CraftTest {

    Craft craft;
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

    /**
     * Erreur introuvée .. En cours ..
     */

    @Ignore
    @Test
    public void getReagentTest() {
        //craft=new Craft();
        Set listKeys = craft.getReagent(Resource.GLASS).keySet();
        Iterator iterateur = listKeys.iterator();
        Resource reagent;
        while (iterateur.hasNext()) {
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.QUARTZ);
            assertEquals((int) craft.getReagent(Resource.GLASS).get(reagent), 10);
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.WOOD);
            assertEquals((int) craft.getReagent(Resource.GLASS).get(reagent), 5);
        }
        listKeys = craft.getReagent(Resource.INGOT).keySet();
        iterateur = listKeys.iterator();
        while (iterateur.hasNext()) {
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.ORE);
            assertEquals((int) craft.getReagent(Resource.INGOT).get(reagent), 5);
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.WOOD);
            assertEquals((int) craft.getReagent(Resource.INGOT).get(reagent), 5);
        }
        listKeys = craft.getReagent(Resource.LEATHER).keySet();
        iterateur = listKeys.iterator();
        while (iterateur.hasNext()) {
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.FUR);
            assertEquals((int) craft.getReagent(Resource.LEATHER).get(reagent), 3);
        }
        listKeys = craft.getReagent(Resource.PLANK).keySet();
        iterateur = listKeys.iterator();
        while (iterateur.hasNext()) {
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.ORE);
            assertEquals((int) craft.getReagent(Resource.PLANK).get(reagent), 1);
        }
        listKeys = craft.getReagent(Resource.RUM).keySet();
        iterateur = listKeys.iterator();
        while (iterateur.hasNext()) {
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.FRUITS);
            assertEquals((int) craft.getReagent(Resource.RUM).get(reagent), 1);
            reagent = (Resource) iterateur.next();
            assertEquals(reagent, Resource.SUGAR_CANE);
            assertEquals((int) craft.getReagent(Resource.RUM).get(reagent), 10);
        }
    }

}
