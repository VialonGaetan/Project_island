package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author romain
 * Created on 15/01/2017.
 */
public class FlyToIslandTest2
{
    private FlyToIsland flyToIsland;

    /**
     * test if nextAction is an instance of Stop
     * @throws InvalidMapException, if bad coordinates
     */
    @Test
    public void stopAction() throws InvalidMapException
    {
        IslandMap islandMap = new IslandMap();

        flyToIsland = new FlyToIsland(islandMap, Direction.E, Direction.W, Direction.W, 0);
        assertTrue(flyToIsland.nextAction() instanceof Stop);
        islandMap.setOutOfRange(Direction.E, 1);
        islandMap.setOutOfRange(Direction.N, 0);
        flyToIsland = new FlyToIsland(islandMap, Direction.E, Direction.N, Direction.W, 0);
        assertTrue(flyToIsland.nextAction() instanceof Stop);
    }

    /**
     * test if nextAction is an instance of Fly
     * @throws InvalidMapException, if bad coordinates
     */
    @Test
    public void flyAction() throws InvalidMapException
    {
        IslandMap islandMap = new IslandMap();
        islandMap.setGround(Direction.E, 10);
        flyToIsland = new FlyToIsland(islandMap, Direction.E, Direction.E, Direction.W, 0);
        assertTrue(flyToIsland.nextAction() instanceof Fly);
    }

    /**
     * test if nextAction is an instance of Heading
     * @throws InvalidMapException, if bad coordinates
     */
    @Test
    public void headingAction() throws InvalidMapException
    {
        IslandMap islandMap = new IslandMap();
        islandMap.setGround(Direction.E, 10);
        flyToIsland = new FlyToIsland(islandMap, Direction.E, Direction.W, null, 0);
        assertTrue(flyToIsland.nextAction() instanceof Heading);
    }

    /**
     * test if nextAction is an instance of FlyToIsland
     * @throws InvalidMapException, if bad coordinates
     */
    @Test
    public void flyToIslandSetResult() throws InvalidMapException
    {
        flyToIsland = new FlyToIsland(new IslandMap(), Direction.E, Direction.W, null, 10);
        assertTrue(flyToIsland.setResult(new Area() {
            @Override
            public Area putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof FlyToIsland);

        flyToIsland = new FlyToIsland(new IslandMap(), Direction.E, Direction.W, null, 0);
        assertTrue(flyToIsland.setResult(new Area() {
            @Override
            public Area putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof FlyToIsland);
    }

    /**
     * test if nextAction is an instance of FlyToIsland
     * @throws InvalidMapException, if bad coordinates
     */
    @Test
    public void turnSetResult() throws InvalidMapException
    {
        flyToIsland = new FlyToIsland(new IslandMap(), Direction.E, Direction.W, Direction.E, 0);
        assertTrue(flyToIsland.setResult(new Area() {
            @Override
            public Area putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof Turn);

        flyToIsland = new FlyToIsland(new IslandMap(), Direction.E, Direction.W, Direction.W, 0);
        assertTrue(flyToIsland.setResult(new Area() {
            @Override
            public Area putResults(String result) {
                return null;
            }

            @Override
            public String toJSON() {
                return null;
            }
        }) instanceof Turn);
    }
}
