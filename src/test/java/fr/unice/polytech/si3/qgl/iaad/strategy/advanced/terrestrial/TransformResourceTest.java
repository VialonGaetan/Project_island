package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Gaetan Vialon
 *         Created the 24/03/2017.
 */
public class TransformResourceTest {


    private Context context;
    private Crew crew;
    private IslandMap map;


    @Before
    public void setUp() throws Exception {
        crew = new Crew(1,new Point(1,1));
        map = new IslandMap();
        context = mock(Context.class);
        when(context.getBudget()).thenReturn(new Budget(10000));
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(1);
        Contract contract = new Contract();
        contract.addContract(new ManufacturedContract(Manufactured.INGOT,5));
        when(context.getContract()).thenReturn(contract);
    }

    @Test
    public void transformTest(){
        Protocol protocol = new TransformResource(context,map,crew,context.getContract().get(Manufactured.INGOT));
        assertFalse(crew.haveEnoughResourceToTransform(context.getContract().get(Manufactured.INGOT)));
        crew.collect(PrimaryResource.WOOD,27);// Simulate exploiting ressource
        crew.collect(PrimaryResource.ORE,27);
        assertTrue(crew.haveEnoughResourceToTransform(context.getContract().get(Manufactured.INGOT)));
        assertEquals(protocol.takeDecision().getActionEnum(), ArgActions.TRANSFORM);
        JsonResult jsonResult = new JsonResult(new JSONObject("{ \"cost\": 5, \"extras\": { \"production\": 1, \"kind\": \"INGOT\" },\"status\": \"OK\" }"));
        protocol.acknowledgeResults(jsonResult);
        assertEquals(context.getContract().get(Manufactured.INGOT).getCollectedQuantity(),1);
        assertFalse(context.getContract().get(Manufactured.INGOT).isComplete());
        assertFalse(context.getContract().allCompleted());

        jsonResult = new JsonResult(new JSONObject("{ \"cost\": 5, \"extras\": { \"production\": 5, \"kind\": \"INGOT\" },\"status\": \"OK\" }"));
        protocol.acknowledgeResults(jsonResult);
        assertEquals(context.getContract().get(Manufactured.INGOT).getCollectedQuantity(),1+5);
        assertTrue(context.getContract().get(Manufactured.INGOT).isComplete());
        assertTrue(context.getContract().allCompleted());
    }

}