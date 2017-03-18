package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.engine.Engine;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class ContractTest
{
    @Test
    public void fishContract() throws Exception
    {
        Basket basket = new Basket();
        basket.put(Resource.FISH, 1000);
        basket.put(Resource.GLASS, 50);

        Contract contract = new Contract(basket);
        assertEquals(new Integer(1000), contract.get(Resource.FISH));
        assertFalse(contract.completed(Resource.FISH));
        assertFalse(contract.allCompleted());

        contract.collect(Resource.FISH, 999);
        assertEquals(new Integer(1), contract.get(Resource.FISH));
        assertFalse(contract.completed(Resource.FISH));
        assertFalse(contract.allCompleted());

        contract.collect(Resource.FISH, 1);
        assertEquals(new Integer(0), contract.get(Resource.FISH));
        assertTrue(contract.completed(Resource.FISH));
        assertFalse(contract.allCompleted());

        contract.collect(Resource.FISH, 1);
        assertEquals(new Integer(0), contract.get(Resource.FISH));
        assertTrue(contract.completed(Resource.FISH));
        assertFalse(contract.allCompleted());

        contract.collect(Resource.GLASS, 50);
        assertEquals(new Integer(0), contract.get(Resource.GLASS));
        assertTrue(contract.completed(Resource.GLASS));
        assertTrue(contract.allCompleted());
    }
}