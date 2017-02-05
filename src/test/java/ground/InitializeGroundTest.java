package ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.ground.InitialisationGround;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class InitializeGroundTest{

        private InitialisationGround init;
        private HashMap<Resource,Integer> contrat=new HashMap<>();
        private Ground ground;

        @Before
        public void Init(){
            init = new InitialisationGround(contrat, Direction.N);
        }

        @Test
        public void nextAction()
        {
            for (int i = 0; i < 10; i++) {
                assertEquals(init.nextAction().getClass(),new Explore().getClass());
            }
        }
}
