package fr.unice.polytech.si3.qgl.iaad.islandMap;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author romain
 * Created on 02/02/17.
 */
public class EmergencyTest
{
    EmergencySite emergencySite;

    @Test
    public void constructors()
    {
        String id = "id";

        emergencySite = new EmergencySite(id);

        assertEquals(emergencySite.getId(), id);

        EmergencySite emergencySite = new EmergencySite(this.emergencySite);
        assertEquals(emergencySite.getId(), this.emergencySite.getId());
    }
}
