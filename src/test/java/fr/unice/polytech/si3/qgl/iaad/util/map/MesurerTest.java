package fr.unice.polytech.si3.qgl.iaad.util.map;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Théo on 14/02/2017.
 */
public class MesurerTest {

    IslandMap islandMap;
    Creek creek1, creek2;
    List<Creek> listCreek1, listCreek2;
    EmergencySite emergencySite;

    /**
     * create a map with somme objects, an emergency site and some creeks
     */
    @Before
    public void init(){
        islandMap = new IslandMap();
        listCreek1=new ArrayList<>();
        listCreek2=new ArrayList<>();
        List<EmergencySite> emergencySites = new ArrayList<>();
        emergencySite = new EmergencySite("emergency");
        creek1=new Creek("ok");
        creek2=new Creek("pas ok");
        listCreek1.add(creek1);
        listCreek2.add(creek2);
        emergencySites.add(emergencySite);
        islandMap =new IslandMap();
        islandMap.grow(Compass.E,10);
        islandMap.grow(Compass.S,10);
        islandMap.getTile(new Point()).addCreeks(listCreek1);
        islandMap.getTile(new Point(5,0)).addCreeks(listCreek2);
        islandMap.getTile(new Point(0,0)).addEmergencySites(emergencySites);
    }

    /**
     * test what is the closest creek from the emergency site
     */
    @Test
    public void findClosestCreekTest(){
        assertEquals(Mesurer.findClosestCreek(islandMap),creek1);
        assertFalse(Mesurer.findClosestCreek(islandMap).equals(creek2));
    }

    /**
     * test if the id of the closest creek is correct
     */
    @Test
    public void findClosestCreekIdTest(){
        assertEquals(Mesurer.findClosestCreekId(islandMap),"ok");
        assertFalse(Mesurer.findClosestCreekId(islandMap).equals("pas ok"));
    }
}
