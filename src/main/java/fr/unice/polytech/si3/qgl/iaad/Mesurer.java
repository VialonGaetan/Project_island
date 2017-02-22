package fr.unice.polytech.si3.qgl.iaad;

import fr.unice.polytech.si3.qgl.iaad.future.IMesurer;
import fr.unice.polytech.si3.qgl.iaad.board.Board;
import fr.unice.polytech.si3.qgl.iaad.board.Creek;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Théo on 13/02/2017.
 */
public final class Mesurer implements IMesurer{


    private static List<Point> listCreek= new ArrayList<>();
    private static List<Point> listEmergencySite= new ArrayList<>();
    private Mesurer(){
    }



    public static Creek findClosestCreek(Board board) {
        Set listKeys=board.getMap().keySet();
        Iterator iterateur=listKeys.iterator();
        while(iterateur.hasNext())
        {
            Point key= (Point) iterateur.next();
            if (board.getMap().get(key).getCreeks().size()!=0){
                listCreek.add(key);
            }
            if (board.getMap().get(key).getEmergencySites().size()!=0){
                listEmergencySite.add(key);
            }
        }
        if (listCreek.size()==0 || listEmergencySite.size()==0) return null;
        Point closestCreekLocation = listCreek.get(0);
        Point emergencySiteLocation = listEmergencySite.get(0);
        double distance = closestCreekLocation.distance(emergencySiteLocation);
        for(int i=0; i<listCreek.size(); i++)
        {
            if(listCreek.get(i).distance(emergencySiteLocation) < distance)
            {
                distance = listCreek.get(i).distance(emergencySiteLocation);
                closestCreekLocation = listCreek.get(i);
            }
        }
        return board.getMap().get(closestCreekLocation).getCreeks().get(0);
    }


    public static String findClosestCreekId(Board board) {return findClosestCreek(board).getId();}

}
