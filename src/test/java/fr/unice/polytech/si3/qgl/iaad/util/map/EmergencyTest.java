package fr.unice.polytech.si3.qgl.iaad.util.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author romain
 * Created on 02/02/17.
 */
public class EmergencyTest
{
    EmergencySite emergencySite;

    @Before
    public void constructor() { emergencySite = new EmergencySite("id"); }

    @Test
    public void getIdTest() { assertTrue(emergencySite.getId().equals("id")); }

    @Test
    public void equalsTest()
    {
        assertTrue(emergencySite.equals(new EmergencySite("id")));
        assertTrue(emergencySite.equals(emergencySite));
        assertFalse(emergencySite.equals(null));
        assertFalse(emergencySite.equals(new Object()));
    }

    @Test
    public void hashCodeTest() { assertEquals(3355, emergencySite.hashCode()); }
}
