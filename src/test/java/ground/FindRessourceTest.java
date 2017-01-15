package ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Exploit;
import fr.unice.polytech.si3.qgl.iaad.actions.Glimpse;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.ground.FindRessource;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class FindRessourceTest {

    private FindRessource find;
    private int nbofResource;
    private Resource resource;
    private HashMap<Resource,Integer> contrat=new HashMap<>();
    private Ground ground;
    private Direction direction;

    @Before
    public void Init(){
        nbofResource=10;
        resource=Resource.FUR;
        direction= Direction.N;
        ground = new Exploit(resource);
        find = new FindRessource(contrat,direction);
    }

    @Test
    public void nextAction()
    {
        assertEquals(find.nextAction().getClass(),new Glimpse(Direction.N,4).getClass());
        for (int i = 0; i < 10; i++) {
            find = new FindRessource(contrat,direction,i);
            assertEquals(find.nextAction().getClass(),new Glimpse(Direction.N,i).getClass());
        }
    }
}
