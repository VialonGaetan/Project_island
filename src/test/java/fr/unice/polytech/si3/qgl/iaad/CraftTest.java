package fr.unice.polytech.si3.qgl.iaad;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Craft;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Théo on 22/02/2017.
 */
public class CraftTest {

    Craft craft;

    @Before
    public void init() {
        craft = new Craft();
    }

    @Test
    public void isPrimaryTest() {
        assertTrue(craft.isPrimary(Resource.FISH));
        assertTrue(craft.isPrimary(Resource.FLOWER));
        assertTrue(craft.isPrimary(Resource.FRUITS));
        assertTrue(craft.isPrimary(Resource.FUR));
        assertTrue(craft.isPrimary(Resource.ORE));
        assertTrue(craft.isPrimary(Resource.SUGAR_CANE));
        assertTrue(craft.isPrimary(Resource.QUARTZ));
        assertFalse(craft.isPrimary(Resource.RUM));
        assertFalse(craft.isPrimary(Resource.GLASS));
        assertFalse(craft.isPrimary(Resource.INGOT));
        assertFalse(craft.isPrimary(Resource.LEATHER));
        assertFalse(craft.isPrimary(Resource.PLANK));
    }

    /**
     * Erreur introuvée .. En cours ..
     */
    /*
    @Test
    public void getReagentTest() {
        craft=new Craft();
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
    }*/

}
