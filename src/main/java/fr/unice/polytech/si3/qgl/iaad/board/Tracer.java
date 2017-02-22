package fr.unice.polytech.si3.qgl.iaad.board;

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

    public Boolean isTopAnOldPosition(IslandMap map, Point point) throws InvalidMapException {
        if (point.getX()<0 || point.getX()>map.getHorizontalDimension() || point.getY()<0 || point.getY()>map.getVerticalDimension()) throw new InvalidMapException();
        Point newPoint = new Point((int) point.getX(),(int) point.getY()-1);
        if (newPoint.getX()<0 || newPoint.getX()>map.getHorizontalDimension() || newPoint.getY()<0 || newPoint.getY()>map.getVerticalDimension()) return null;
        for (Point p : oldPositions){
            if (p.equals(newPoint)) return true;
        }
        return false;
    }

    public Boolean isBottomAnOldPosition(IslandMap map, Point point) throws InvalidMapException {
        if (point.getX()<0 || point.getX()>map.getHorizontalDimension() || point.getY()<0 || point.getY()>map.getVerticalDimension()) throw new InvalidMapException();
        Point newPoint = new Point((int) point.getX(),(int) point.getY()+1);
        if (newPoint.getX()<0 || newPoint.getX()>map.getHorizontalDimension() || newPoint.getY()<0 || newPoint.getY()>map.getVerticalDimension()) return null;
        for (Point p : oldPositions){
            if (p.equals(newPoint)) return true;
        }
        return false;
    }

    public Boolean isLeftAnOldPosition(IslandMap map, Point point) throws InvalidMapException {
        if (point.getX()<0 || point.getX()>map.getHorizontalDimension() || point.getY()<0 || point.getY()>map.getVerticalDimension()) throw new InvalidMapException();
        Point newPoint = new Point((int) point.getX()-1,(int) point.getY());
        if (newPoint.getX()<0 || newPoint.getX()>map.getHorizontalDimension() || newPoint.getY()<0 || newPoint.getY()>map.getVerticalDimension()) return null;
        for (Point p : oldPositions){
            if (p.equals(newPoint)) return true;
        }
        return false;
    }

    public Boolean isRightAnOldPosition(IslandMap map, Point point) throws InvalidMapException {
        if (point.getX()<0 || point.getX()>map.getHorizontalDimension() || point.getY()<0 || point.getY()>map.getVerticalDimension()) throw new InvalidMapException();
        Point newPoint = new Point((int) point.getX()+1,(int) point.getY());
        if (newPoint.getX()<0 || newPoint.getX()>map.getHorizontalDimension() || newPoint.getY()<0 || newPoint.getY()>map.getVerticalDimension()) return null;
        for (Point p : oldPositions){
            if (p.equals(newPoint)) return true;
        }
        return false;
    }






}