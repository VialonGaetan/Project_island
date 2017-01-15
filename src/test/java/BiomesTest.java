import fr.unice.polytech.si3.qgl.iaad.Biomes;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class BiomesTest {

    @Test
    public void OceanTest(){
        Biomes biomes = Biomes.OCEAN;
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(Resource.FISH);
        assertEquals(biomes.getAssociateResources(),resourceList);
        assertEquals(biomes.getAssociateResources().get(0),Resource.FISH);
        assertEquals(biomes.getAssociateResources().size(),1);
    }
}
