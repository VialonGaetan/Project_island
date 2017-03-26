package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.*;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.awt.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Gaetan Vialon
 *         Created the 25/03/2017.
 */
public class ExploreTileTest {

    private Context context;
    private Crew crew;
    private IslandMap map;
    private JsonResult jsonResult;
    private Protocol protocol;

    @Before
    public void setUp() throws Exception {
        crew = new Crew(1,new Point(0,0));
        map = new IslandMap();
        map.grow(Compass.N,0);
        map.grow(Compass.W,0);
        map.grow(Compass.S,10);
        map.grow(Compass.E,10);
        context = mock(Context.class);
        when(context.getBudget()).thenReturn(new Budget(10000));
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        Contract contract = new Contract();
        contract.addContract(new PrimaryContract(PrimaryResource.ORE,100));
        contract.addContract(new ManufacturedContract(Manufactured.LEATHER,5));
        contract.addContract(new PrimaryContract(PrimaryResource.QUARTZ,50));
        contract.addContract(new ManufacturedContract(Manufactured.GLASS,15));
        when(context.getContract()).thenReturn(contract);
    }


    @Test
    public void exploreTestAndWorthToExploit() throws Exception {

        protocol = new ExploreTile(context,map,crew);
        assertFalse(map.getTile(crew.getLocation()).isAlready(GroundActionTile.VISITED));
        assertTrue(map.getTile(crew.getLocation()).getResourceInformationList().isEmpty());
        protocol.takeDecision();
        assertNotEquals(protocol.takeDecision(),null);
        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.EXPLORE);

        jsonResult = new JsonResult(new JSONObject("{\n" +
                "  \"cost\": 5,\n" +
                "  \"extras\": {\n" +
                "    \"resources\": [\n" +
                "      { \"amount\": \"HIGH\", \"resource\": \"FUR\", \"cond\": \"FAIR\" },\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"WOOD\", \"cond\": \"HARSH\" }\n" +
                "    ],\n" +
                "    \"pois\": [ \"creek-id\" ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}"));

        protocol=protocol.acknowledgeResults(jsonResult);
        List<ResourceInformation> resourceInformations = new ArrayList<>();
        resourceInformations.add(new ResourceInformation(PrimaryResource.FUR, ResourceAmount.HIGH, ResourceCondition.FAIR));
        resourceInformations.add(new ResourceInformation(PrimaryResource.WOOD, ResourceAmount.LOW, ResourceCondition.HARSH));

        for (int i = 0; i < resourceInformations.size(); i++) {

            assertEquals(map.getTile(crew.getLocation()).getResourceInformationList().get(i).getResource(),resourceInformations.get(i).getResource());
            assertEquals(map.getTile(crew.getLocation()).getResourceInformationList().get(i).getResourceAmount(),resourceInformations.get(i).getResourceAmount());
            assertEquals(map.getTile(crew.getLocation()).getResourceInformationList().get(i).getResourceCondition(),resourceInformations.get(i).getResourceCondition());

        }

        assertTrue(map.getTile(crew.getLocation()).isAlready(GroundActionTile.VISITED));

        //Next Action Exploit because it's worth to exploit FUR
        assertEquals(protocol.takeDecision().getActionEnum(),ArgActions.EXPLOIT);

    }


    @Test
    public void exploreTestAndNotWorthToExploit() throws Exception {

        protocol = new ExploreTile(context,map,crew);
        assertFalse(map.getTile(crew.getLocation()).isAlready(GroundActionTile.VISITED));
        assertTrue(map.getTile(crew.getLocation()).getResourceInformationList().isEmpty());
        protocol.takeDecision();
        assertNotEquals(protocol.takeDecision(),null);
        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.EXPLORE);

        jsonResult = new JsonResult(new JSONObject("{\n" +
                "  \"cost\": 5,\n" +
                "  \"extras\": {\n" +
                "    \"resources\": [\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"QUARTZ\", \"cond\": \"HARSH\" },\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"ORE\", \"cond\": \"HARSH\" }\n" +
                "    ],\n" +
                "    \"pois\": [ \"creek-id\" ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}"));

        protocol=protocol.acknowledgeResults(jsonResult);
        List<ResourceInformation> resourceInformations = new ArrayList<>();
        resourceInformations.add(new ResourceInformation(PrimaryResource.QUARTZ, ResourceAmount.LOW, ResourceCondition.HARSH));
        resourceInformations.add(new ResourceInformation(PrimaryResource.ORE, ResourceAmount.LOW, ResourceCondition.HARSH));

        for (int i = 0; i < resourceInformations.size(); i++) {

            assertEquals(map.getTile(crew.getLocation()).getResourceInformationList().get(i).getResource(),resourceInformations.get(i).getResource());
            assertEquals(map.getTile(crew.getLocation()).getResourceInformationList().get(i).getResourceAmount(),resourceInformations.get(i).getResourceAmount());
            assertEquals(map.getTile(crew.getLocation()).getResourceInformationList().get(i).getResourceCondition(),resourceInformations.get(i).getResourceCondition());

        }

        assertTrue(map.getTile(crew.getLocation()).isAlready(GroundActionTile.VISITED));

        //Next Action not Exploit because it's not worth to exploit ORE and QUARTZ
        assertNotEquals(protocol.takeDecision().getActionEnum(),ArgActions.EXPLOIT);

    }
}