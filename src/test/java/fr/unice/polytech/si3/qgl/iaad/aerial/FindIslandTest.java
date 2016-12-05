package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Théo
 */
public class FindIslandTest {


        private FindIsland find = new FindIsland(map,Direction.E, Direction.E);
        private IslandMap map;

        AreaResult result = new EchoResult("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        @Test
        public void setResultTest()
        {
            assertTrue(find.setResult(result).getClass().isInstance(new FlyToIsland(map, Direction.E, Direction.S, Direction.E, result.getRange())));
        }
}