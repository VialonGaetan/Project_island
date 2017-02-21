import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Mesurer;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Board;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;
import fr.unice.polytech.si3.qgl.iaad.islandMap.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Tile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Théo on 14/02/2017.
 */
public class MesurerTest {

    Board board;
    Tile tile;
    Creek creek1, creek2;
    List<Creek> listCreek1, listCreek2;
    Mesurer mesurer;
    EmergencySite emergencySite;

    @Before
    public void init(){
        board = new Board();
        listCreek1=new ArrayList<>();
        listCreek2=new ArrayList<>();
        List<EmergencySite> emergencySites = new ArrayList<>();
        emergencySite = new EmergencySite("emergency");
        creek1=new Creek("ok");
        creek2=new Creek("pas ok");
        listCreek1.add(creek1);
        listCreek2.add(creek2);
        emergencySites.add(emergencySite);
        board=new Board();
        board.grow(Direction.E,10);
        board.grow(Direction.S,10);
        board.getTile(new Point(1,2)).addCreeks(listCreek1);
        System.out.println("ok");
        board.getTile(new Point(5,5)).addCreeks(listCreek2);
        board.getTile(new Point(0,0)).addEmergencySites(emergencySites);
    }

   /* @Test
    public void findClosestCreekTest(){
        assertEquals(Mesurer.findClosestCreek(board),creek1);
    }

    @Test
    public void findClosestCreekIdTest(){

    } */
}
