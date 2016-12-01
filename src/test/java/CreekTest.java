
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import java.util.List;

/**
 * Created by romain on 27/11/16.
 */


public class CreekTest
{
    private Creek creek;

    /**
     * addElement creeks
     * test coordinates and number of creeks
     */
    @Test
    public void constructorTest()
    {
        IslandMap islandMap=new IslandMap();
        creek=new Creek(islandMap);

        assertEquals(0, creek.getCreeks().size());
        islandMap.addElements(Element.WOOD);

        creek=new Creek(islandMap);
        assertEquals(0, creek.getCreeks().size());

        islandMap.setOutOfRange(Direction.E, 5);

        for(int i=0; i<3; i++)
        {
            islandMap.addElements(Element.CREEK);
            islandMap.moveDroneCorrectly(Direction.E);
        }

        islandMap.setOutOfRange(Direction.S, 8);

        for(int i=0; i<5; i++)
        {
            islandMap.moveDroneCorrectly(Direction.S);
            islandMap.addElements(Element.CREEK);
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

    @Test
    public void addCreekTest()
    {
        creek=new Creek(new IslandMap());
        creek.addCreek(5, 3);
        assertEquals(creek.getCreeks().size(), 1);
        int coordinateX=creek.getCreeks().get(0).get(0), coordinateY=creek.getCreeks().get(0).get(1);
        assertEquals(coordinateX, 5);
        assertEquals(coordinateY, 3);
        creek.addCreek(5, 3);
        assertEquals(creek.getCreeks().size(), 1);
    }

    @Test
    public void getClosestTest()
    {
        creek=new Creek(new IslandMap());
        creek.addCreek(0, 0);
        creek.addCreek(0, 1);
        creek.addCreek(1, 0);

        List<Integer> creekClosest=creek.closest(2,0);
        int creekClosestX=creekClosest.get(0), creekClosestY=creekClosest.get(1);

        assertEquals(1, creekClosestX);
        assertEquals(0, creekClosestY);
    }
}