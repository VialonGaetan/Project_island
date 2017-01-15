package ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Exploit;
import fr.unice.polytech.si3.qgl.iaad.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.actions.StopGround;
import fr.unice.polytech.si3.qgl.iaad.ground.Exploration;
import fr.unice.polytech.si3.qgl.iaad.ground.Move;
import fr.unice.polytech.si3.qgl.iaad.ground.StopExplorer;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class ExplorationTest {

    private int budget;
    private IslandMap islandMap;
    private Context context;
    private Exploration exploration;
    @Before
    public void Init(){
        islandMap = new IslandMap();
        context = new Context(new JSONObject("{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}"));
        budget= 10;
        exploration = new Exploration(budget, islandMap,context);
    }
    @Test
    public void doAction()
    {
        assertEquals(exploration.doAction().getClass(),new StopGround().getClass());
        budget = 1000;
        exploration = new Exploration(budget, islandMap,context);
        assertEquals(exploration.doAction().getClass(),new Explore().getClass());
    }
}
