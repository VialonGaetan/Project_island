package fr.unice.polytech.si3.qgl.iaad.util.resource;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class BiomeTest {

    @Test
    public void OceanTest(){
        Biome biome = Biome.OCEAN;
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(PrimaryResource.FISH);
        assertEquals(biome.getAssociateResources(),resourceList);
        assertEquals(biome.getAssociateResources().get(0),PrimaryResource.FISH);
        assertEquals(biome.getAssociateResources().size(),1);
    }
}
