package fr.unice.polytech.si3.qgl.iaad;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Théo on 20/12/2016.
 */
public class ResourceTest {

    @Test
    public void getNameTest()
    {
        Resource res = Resource.FISH;
        assertTrue(res.toString().equals("FISH"));
        res=Resource.FLOWER;
        assertTrue(res.toString().equals("FLOWER"));
        res=Resource.FRUITS;
        assertTrue(res.toString().equals("FRUITS"));
        res=Resource.FUR;
        assertTrue(res.toString().equals("FUR"));
        res=Resource.GLASS;
        assertTrue(res.toString().equals("GLASS"));
        res=Resource.INGOT;
        assertTrue(res.toString().equals("INGOT"));
        res=Resource.LEATHER;
        assertTrue(res.toString().equals("LEATHER"));
        res=Resource.ORE;
        assertTrue(res.toString().equals("ORE"));
        res=Resource.PLANK;
        assertTrue(res.toString().equals("PLANK"));
        res=Resource.QUARTZ;
        assertTrue(res.toString().equals("QUARTZ"));
        res=Resource.RUM;
        assertTrue(res.toString().equals("RUM"));
        res=Resource.SUGAR_CANE;
        assertTrue(res.toString().equals("SUGAR_CANE"));
        res=Resource.WOOD;
        assertTrue(res.toString().equals("WOOD"));

    }

}
