package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Théo
 */
public class FindIslandTest {

        private IslandMap map;
        private FindIsland find = new FindIsland(map,Direction.E, Direction.E);

        AreaResult result = new EchoResult("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        @Test
        public void setResultTest() throws InvalidMapException {
            assertTrue(find.setResult(result).getClass().isInstance(new FlyToIsland(map, Direction.E, Direction.S, Direction.E, result.getRange())));
        }
}