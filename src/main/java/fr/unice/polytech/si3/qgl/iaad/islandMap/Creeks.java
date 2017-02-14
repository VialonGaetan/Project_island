package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Théo
 */

public class Creeks
{
    private List<Point> creekLocations;
    private IslandMap islandMap;
    private Point emergencySiteLocation;
    private Map<Point, String> creeks;

    /**
     * Constructor of the creekLocations
     * @param islandMap, must be the current map
     */
    public Creeks(IslandMap islandMap)
    {
        this.islandMap = islandMap;
        creekLocations = getCreekLocations();
        emergencySiteLocation = null;

        for(int x=0; x<islandMap.getHorizontalDimension(); x++)
        {
            for (int y = 0; y < islandMap.getVerticalDimension(); y++)
            {
                try
                {
                    if (islandMap.hasElement(new Point(x, y), Element.EMERGENCY_SITE))
                    {
                        emergencySiteLocation = new Point(x, y);
                    }
                }

                catch (InvalidMapException exception) { emergencySiteLocation = null; }
            }
        }
    }

    private List<Point> getCreekLocations()
    {
        List<Point> locations = new ArrayList<>();
        creeks = new HashMap<>();

        for(int x=0; x<islandMap.getHorizontalDimension(); x++)
        {
            for(int y=0; y<islandMap.getVerticalDimension(); y++)
            {
                try
                {
                    if(islandMap.hasElement(new Point(x, y), Element.CREEK))
                    {
                        locations.add(new Point(x, y));
                        creeks.put(new Point(x, y), islandMap.getCreekIds(new Point(x, y))[0]);
                    }
                }

                catch(InvalidMapException exception) { return new ArrayList<>(); }
            }
        }

        return locations;
    }

    public Point getClosestCreekLocation() throws InvalidMapException
    {
        if(creekLocations.size() == 0 || emergencySiteLocation == null)
        {
            throw new InvalidMapException();
        }

        Point closestCreekLocation = creekLocations.get(0);
        double distance = closestCreekLocation.distance(emergencySiteLocation);

        for(int i=0; i<creekLocations.size(); i++)
        {
            if(creekLocations.get(i).distance(emergencySiteLocation) < distance)
            {
                distance = creekLocations.get(i).distance(emergencySiteLocation);
                closestCreekLocation = creekLocations.get(i);
            }
        }

        return closestCreekLocation;
    }

    public String getClosestCreekId() throws InvalidMapException
    {
        Point closestCreekLocation = getClosestCreekLocation();

        if(creeks.get(closestCreekLocation).equals("") || emergencySiteLocation == null)
        {
            throw new InvalidMapException();
        }

        return creeks.get(closestCreekLocation);
    }
/*
    public Point getClosest() throws InvalidMapException
    {
        Double min;
        Double distance;
        Point closest;

        for (int j = 0; j < islandMap.getVerticalDimension(); j++) {
            for (int i = 0; i < islandMap.getHorizontalDimension(); i++) {
                if (islandMap.hasElement(new Point(i, j), Element.CREEK)) {
                    this.creekLocations.add(new Point(i, j));
                }
                if (islandMap.hasElement(new Point(i, j), Element.EMERGENCY_SITE)) {
                    this.emergencySiteLocation = new Point(i, j);
                }
            }
        }
        int size = creekLocations.size();
        if (size == 0)
            return null;

        min = creekLocations.get(0).distance(emergencySiteLocation);
        System.out.println("mdr2");
        closest = creekLocations.get(0);
        for (int i = 1; i < size; i++) {
            distance = creekLocations.get(i).distance(emergencySiteLocation);
            if (distance < min) {
                closest = creekLocations.get(i);
                min=distance;
            }
        }
        return closest;
    }

    public String getClosestID(Point point) throws InvalidMapException
    {
        return islandMap.getCreekIds(point)[0];
    }
*/
}