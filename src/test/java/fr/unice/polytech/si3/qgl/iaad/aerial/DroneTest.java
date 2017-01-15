package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
public class DroneTest
{
    static final int TEST = 1000;
    static final String CREEK = "creek";
    private Drone drone;
    private int budget;
    private Drone droneWithoutBudget;


    @Before
    public void setUp() throws Exception
    {
        budget = Drone.LOW_BUDGET + 1;
        int lowBudget = Drone.LOW_BUDGET - 1;
        Direction heading = Direction.E;
        IslandMap map = new IslandMap();
        drone = new Drone(budget, heading, map);
        droneWithoutBudget = new Drone(lowBudget, heading, map);
    }

    @Test
    public void doAction() throws Exception
    {
        Action action = drone.doAction();
        assertNotNull(action);
        assertTrue(action instanceof Area);
        assertTrue(droneWithoutBudget.doAction() instanceof Stop);
    }

    @Test
    public void randomAction() throws Exception
    {
        for (int i = 0; i < TEST; i++)
        {
            IslandMap map = new IslandMap();
            int budget = 10000;
            JSONObject jsonObject;
            Drone drone = new Drone(budget, Direction.E, map);
            Area action = ((Area) drone.doAction());

            while (!(action instanceof Stop))
            {
                jsonObject = randomJSON();
                action.putResults(jsonObject.toString());
                drone.getResult(action);
                budget -= (int) jsonObject.get("cost");
                assertEquals(budget, drone.getBudget());
                action = ((Area) drone.doAction());
            }
        }
    }

    @Test
    public void findEmergency() throws Exception
    {
        Drone.SEARCH_EMERGENCY_SITE = true;
        randomAction();
    }

    @Test
    public void getResult() throws Exception
    {
        int cost = 10;
        Area action = ((Area) drone.doAction()).putResults(facticeJSON().toString());
        drone.getResult(action);
        assertEquals(budget - cost, drone.getBudget());
    }

    static JSONObject createJSON(int cost)
    {
        return new JSONObject()
                .put("cost", cost)
                .put("status", "OK");
    }

    static JSONObject createEchoJSON(int cost, int range, String found)
    {
        return createJSON(cost).put("extras", new JSONObject()
                .put("range", range)
                .put("found", found));
    }

    static IslandMap facticeMap()
    {
        IslandMap map = new IslandMap();
        map.setOutOfRange(Direction.N, 0);
        map.setOutOfRange(Direction.S, 20);
        map.setGround(Direction.E, 12);
        try
        {
            for (int i = 0; i < 12; i++)
                map.moveLocation(Direction.E);
            map.addCreeks("creek");
            for (int i = 0; i < 12; i++)
                map.moveLocation(Direction.W);
        }
        catch (InvalidMapException e)
        {
        }
        return map;
    }

    static JSONObject facticeJSON()
    {
        return createJSON(10)
                .put("extras", new JSONObject()
                        .put("range", 1)
                        .put("found", Element.GROUND.toString())
                        .put("biomes", new JSONArray()
                                .put(Element.OCEAN.toString()))
                        .put("creeks", new JSONArray()
                                .put(CREEK))
                        .put("sites", new JSONArray()
                                .put("site")));
    }

    static JSONObject randomJSON()
    {
        Random random = new Random();
        String[] creeks = new String[(int) Math.abs(random.nextGaussian() / 16)];
        for (int i = 0; i < creeks.length; i++)
        {
            creeks[i] = CREEK + i;
        }
        return createJSON((int) (2 + Math.random() * 30)).put("extras", new JSONObject()
                .put("range", (int) (Math.random() * 20))
                .put("found", random.nextBoolean() ? Element.OUT_OF_RANGE : Element.GROUND)
                .put("biomes", new JSONArray(Arrays.stream(Element.values()).filter(e -> random.nextBoolean()).map(Enum::toString).toArray()))
                .put("creeks", new JSONArray(creeks))
        );
    }

    static Direction randomDirection()
    {
        return Direction.values()[(int) (Math.random() * 4)];
    }

    static IslandMap randomMap() throws InvalidMapException
    {
        IslandMap map = new IslandMap();
        for (int i = 0; i < Direction.values().length; i++)
        {
            map.setOutOfRange(Direction.values()[i], (int) (Math.random() * 30));
        }
        Direction direction;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 20 + Math.random() * 50; j++)
            {
                direction = randomDirection();
                if (map.getNumberOfAvailablePoints(direction) > 1)
                    map.moveLocation(direction);
                map.addCreeks(CREEK + i);
                if (Math.random() > 0.8)
                    map.addEmergencySite("site");
            }
        }
        return map;
    }

}