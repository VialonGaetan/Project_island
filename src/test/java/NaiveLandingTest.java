
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.NaiveLanding;
import fr.unice.polytech.si3.qgl.iaad.actions.Scout;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.init.Contract;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Théo on 24/12/2016.
 */

public class NaiveLandingTest {

    IslandMap map;
    Context cx;
    Contract ct;
    public List<String[]> previousActions;
    public NaiveLanding nl;


    @Before
    public void init() {
        ct = new Contract(10, "OK");
        cx = new Context(new JSONObject());
        map = new IslandMap();
        previousActions = new ArrayList<>();
        nl = new NaiveLanding(cx, ct, map);
        String[] tmp = new String[5];
        tmp[0] = "SCOUT";
        tmp[1] = "" + Direction.E;
        tmp[2] = "0";
        tmp[3] = "";
        tmp[4] = "";
        previousActions.add(tmp);
        String[] tmp2 = new String[5];
        tmp2[0] = "SCOUT";
        tmp2[1] = "" + Direction.S;
        tmp2[2] = "0";
        tmp2[3] = "";
        tmp2[4] = "";
        previousActions.add(tmp2);
    }
}
 /*   @Test
    public void nextActionTest()
    {

    }
*/ /*
    @Test
    public void scoutAroundTest()
    {
        assertEquals(nl.scoutAround(),new Scout(Direction.S));
    }
*//*
    @Test
    public void moveAroundTest()
    {

    }
}
*/