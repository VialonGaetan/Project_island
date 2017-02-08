package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Théo on 30/01/2017.
 */
public class Tracer{
    private IslandMap islandMap;
    private List<Point> oldPositions;

    public Tracer(IslandMap islandMap){
        this.islandMap=islandMap;
        oldPositions = new ArrayList<>();
    }

    public List<Point> getOldPositions(){
        return oldPositions;
    }

    /**
     * Warning : A utiliser avec map.getLocation();
     * /!\ A utiliser à chaque tour
     * @param map
     * @return if the current position has already been visited
     */
    public Boolean isAnOldPosition(IslandMap map, Point point) throws InvalidMapException {
        if (point.getX()<0 || point.getX()>map.getHorizontalDimension() || point.getY()<0 || point.getY()>map.getVerticalDimension()) throw new InvalidMapException();
        for (Point p : oldPositions){
            if (p.equals(point)) return true;
        }
        oldPositions.add(point);
        return false;
    }


}