import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
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
        resourceList.add(Resource.FISH);
        assertEquals(biome.getAssociateResources(),resourceList);
        assertEquals(biome.getAssociateResources().get(0),Resource.FISH);
        assertEquals(biome.getAssociateResources().size(),1);
    }
}
