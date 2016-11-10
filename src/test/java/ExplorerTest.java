import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.qgl.iaad.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by user on 07/11/2016.
 */
public class ExplorerTest {
    Explorer explorer = new Explorer();

    @Test
    public void TestStop (){
        String Stop ="{\"action\":\"stop\"}";
        assertEquals(Stop, explorer.takeDecision());
    }

}
