
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by romain on 27/11/16.
 */

//sans les tests qui marchent pas

public class CreekTest
{
    private Creek creek;

    /**
     * add creeks
     * test coordinates and number of creeks
     */
    @Test
    public void constructorTest()
    {
        IslandMap islandMap=new IslandMap();
        creek=new Creek(islandMap);

        assertEquals(0, creek.getCreeks().size());
        islandMap.setElement(Element.WOOD);

        creek=new Creek(islandMap);
        assertEquals(0, creek.getCreeks().size());

        islandMap.setOutOfRange(Direction.E, 5);

        for(int i=0; i<3; i++)
        {
            islandMap.setElement(Element.CREEK);
            islandMap.moveDroneCorrectly(Direction.E);
        }

        islandMap.setOutOfRange(Direction.S, 8);

        for(int i=0; i<5; i++)
        {
            islandMap.moveDroneCorrectly(Direction.S);
            islandMap.setElement(Element.CREEK);
        }

        creek=new Creek(islandMap);
        assertEquals(islandMap.numberOfOccurrencesOfTheElement(Element.CREEK), creek.getCreeks().size());

        for(int i=0; i<3; i++)
        {
            int coordinateX=creek.getCreeks().get(i).get(0), coordinateY=creek.getCreeks().get(i).get(1);

            assertEquals(coordinateX, i);
            assertEquals(coordinateY, 0);
        }
    }
}