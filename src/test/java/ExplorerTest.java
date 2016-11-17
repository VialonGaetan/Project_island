import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.si3.qgl.iaad.*;
import fr.unice.polytech.si3.qgl.iaad.Actions.Action;
import fr.unice.polytech.si3.qgl.iaad.Actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.Actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.FlyResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by user on 07/11/2016.
 */
public class ExplorerTest {

    private Action action;
    private Results resultsAction;
    private Explorer explorer;

    @Before
    public void init(){
        explorer = new Explorer();
    }

    @Test
    public void LastActions() {

    }


}
