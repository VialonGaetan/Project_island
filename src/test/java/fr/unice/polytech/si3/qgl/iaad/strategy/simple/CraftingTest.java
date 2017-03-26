package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.SecondContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Théo on 20/03/2017.
 */
public class CraftingTest {

    Context context;
    SecondContract secondContract;
    private Contract contracts;
    Basket basket;
    Contract contract;
    Crafting crafting;
    private JSONObject jsonObject;
    //{ "cost": 5, "extras": { "production": 1, "kind": "GLASS" },"status": "OK" }
    @Before
    public void setUp() throws Exception
    {
        jsonObject = new JSONObject()
                .put("cost", "5")
                .put("status","OK")
                .put("address",new JSONObject()
                        .put("production", 1)
                        .put("kind", "GLASS"));

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

        crafting= new Crafting(Resource.GLASS, secondContract, basket,context, new Crew(1,new Point()),new IslandMap());
    }

    @Test
    public void updateBasket() throws Exception {
        assertTrue(this.basket.get(Resource.QUARTZ)==100);
        assertTrue(this.basket.get(Resource.WOOD)==100);
        this.crafting.updateBasket(Resource.GLASS);
        assertTrue(this.basket.get(Resource.QUARTZ)==90);
        assertTrue(this.basket.get(Resource.WOOD)==95);
    }
}