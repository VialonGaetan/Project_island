package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Théo
 */

public class Creek {

    public List<Point> creeks;
    public IslandMap map;
    public Point emSite;

    /**
     * Constructor of the creeks
     * @param map
     * @throws InvalidMapException
     */
    public Creek(IslandMap map) throws InvalidMapException {
        this.map = map;
        creeks = new ArrayList<Point>();
    }

    /**
     * Retourne un point correspondant à la creek la plus proche de l'emergency Site
     * @param map
     * @return Point
     * @throws InvalidMapException
     */
    public Point getClosest(IslandMap map) throws InvalidMapException {
        Double min;
        Double distance;
        Point closest;
        int[] tempClosest = new int[2];
        {
            for (int j = 0; j < map.getVerticalDimension(); j++) {
                for (int i = 0; i < map.getHorizontalDimension(); i++) {
                    if (map.hasElement(new Point(i, j), Element.CREEK)) {
                        this.creeks.add(new Point(i, j));
                    }
                    if (map.hasElement(new Point(i, j), Element.EMERGENCY_SITE)) {
                        this.emSite = new Point(i, j);
                    }
                }
            }
            int size = creeks.size();
            if (size == 0)
                return null;
            min = creeks.get(0).distance(emSite);
            closest = creeks.get(0);
            for (int i = 1; i < size; i++) {
                distance = creeks.get(i).distance(emSite);
                if (distance < min) {
                    closest = creeks.get(i);
                    min=distance;
                }
            }
            return closest;
        }
    }

    /**
     * Return the id of the closest creek
     * @param point
     * @return String
     * @throws InvalidMapException
     */
    public String getClosestID(Point point) throws InvalidMapException {
        return map.getCreekIds(point)[0];
    }
}