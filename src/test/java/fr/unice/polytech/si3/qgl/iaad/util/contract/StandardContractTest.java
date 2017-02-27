package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class StandardContractTest
{
    @Test
    public void fishContract() throws Exception
    {
        Contract contract = new StandardContract(Resource.FISH, 1000);
        assertEquals(1000, contract.getRemainingAmount());
        assertFalse(contract.isComplete());

        contract.collect(999);
        assertEquals(1, contract.getRemainingAmount());
        assertFalse(contract.isComplete());

        contract.collect(1);
        assertEquals(0, contract.getRemainingAmount());
        assertTrue(contract.isComplete());

        contract.collect(1);
        assertEquals(0, contract.getRemainingAmount());
        assertTrue(contract.isComplete());
    }
}