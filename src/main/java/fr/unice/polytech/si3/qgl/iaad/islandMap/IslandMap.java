package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by romain on 13/11/16.
 */

/**
 * Dynamic map
 * Manage all resources found
 * Handles the movements of the drone
 * Manage setOutOfRange
 * Manage setGround
 * Manage creeks
 * Manage the emergency site
 */

public class IslandMap
{
    /**
     * Matrix that contains elements in string
     */
    private DynamicMatrix bodyMap;

    /**
     * Drone Coordinates
     */
    private Point drone;

    /**
     * Emergency site id
     */
    private String emergencySiteId;

    /**
     * Saves what dimensions are finished in the map
     */
    private boolean dimensionFinished[];

    /**
     * Default constructor
     * Builds the body of the map as an unique point with (0, 0) as coordinates
     * Sets the drone at the point (0, 0)
     * Sets all dimensions as not finished
     * The emergency id is a new String()
     */
    public IslandMap()
    {
        bodyMap = new DynamicMatrix();
        drone = new Point();
        dimensionFinished = new boolean[4];
        emergencySiteId = new String();
    }

    /**
     * Informs if the point at coordinates(x, y) exists in the map
     * @param point
     * @return boolean type : true if the point exist, false otherwise
     */
    public boolean pointExist(Point point) { return bodyMap.pointExist(point.y, point.x); }

    /**
     * Receives a direction
     * Changes dimensions of the map
     * Updates elements coordinates
     * Updates the drone coordinates
     * @param direction, numberOfPoints
     * @throws InvalidMapException, DirectionException
     */
    private void addPoints(Direction direction, int numberOfPoints) throws InvalidMapException
    {
        if(isDirectionFinished(direction)) throw new InvalidMapException();

        numberOfPoints-=getNumberOfAvailablePoints(direction);

        switch(direction)
        {
            case N:
                bodyMap.addLines(0, numberOfPoints);
                drone.move(drone.x, drone.y+numberOfPoints);
                break;
            case S:
                bodyMap.addLines(-1, numberOfPoints);
                break;
            case E:
                bodyMap.addColumns(-1, numberOfPoints);
                break;
            case W:
                bodyMap.addColumns(0, numberOfPoints);
                drone.move(drone.x+numberOfPoints, drone.y);
                break;
        }
    }

    /**
     * Returns the drone coordinates
     * @return Point type
     */
    public Point getDroneCoordinates() { return drone; }

    /**
     * new Fly(direction) in the map
     * @param direction
     * @return boolean type, true if the drone is still in the map and false it it left the map
     * @throws InvalidMapException
     */
    public void moveDrone(Direction direction) throws InvalidMapException
    {
        switch(direction)
        {
            case N:
                drone.move(drone.x, drone.y-1);
                break;
            case S:
                drone.move(drone.x, drone.y+1);
                break;
            case E:
                drone.move(drone.x+1, drone.y);
                break;
            case W:
                drone.move(drone.x-1, drone.y);
                break;
        }

        if(!pointExist(drone)) throw new InvalidMapException();
    }

    /**
     * Informs if this direction is finished
     * @param direction
     * @return boolean type : true if the direction is finished, false otherwise
     */
    public boolean isDirectionFinished(Direction direction) { return dimensionFinished[direction.ordinal()]; }

    /**
     * Sets a direction as finished
     * Changes dimensions of the map
     * Updates elements coordinates
     * Updates the drone coordinates
     * @param direction, numberOfPoints
     * @throws InvalidMapException, DirectionException
     */
    public void setOutOfRange(Direction direction, int numberOfPoints) throws InvalidMapException
    {
        addPoints(direction, numberOfPoints);
        dimensionFinished[direction.ordinal()]=true;
    }

    /**
     * Informs if the map is finished
     * @return boolean type, true if island Map Is Finished and false otherwise
     */
    public boolean isFinished()
    {
        boolean test=true;

        for(int i = 0; i<dimensionFinished.length; i++)
        {
            if(!dimensionFinished[i])
            {
                test=false;
                break;
            }
        }

        return test;
    }

    /**
     * Changes dimensions of the map
     * Updates elements coordinates
     * Updates the drone coordinates
     * Adds Element.GROUND at numberOfPoints+1 compared to the drone location
     * @param direction, numberOfPoints
     * @throws InvalidMapException, DirectionException
     */
    public void setGround(Direction direction, int numberOfPoints) throws InvalidMapException
    {
        numberOfPoints++;

        if(numberOfPoints>getNumberOfAvailablePoints(direction))
            if(!isDirectionFinished(direction))
                addPoints(direction, numberOfPoints);

        String ground=Element.GROUND.toString();

        switch(direction)
        {
            case N:
                addElements(new Point(drone.x, drone.y-numberOfPoints), ground);
                break;
            case S:
                addElements(new Point(drone.x, drone.y+numberOfPoints), ground);
                break;
            case E:
                addElements(new Point(drone.x+numberOfPoints, drone.y), ground);
                break;
            case W:
                addElements(new Point(drone.x-numberOfPoints, drone.y), ground);
                break;
        }
    }

    /**
     * Returns the number of available points in this direction compared to the drone location
     * @param direction
     * @return integer type
     */
    public int getNumberOfAvailablePoints(Direction direction)
    {
        int distance=0;

        switch(direction)
        {
            case N:
                distance=drone.y;
                break;
            case S:
                distance=getVerticalDimension()-drone.y-1;
                break;
            case E:
                distance=getHorizontalDimension()-drone.x-1;
                break;
            case W:
                distance=drone.x;
                break;
        }

        return distance;
    }

    /**
     * Returns horizontal dimension of the map
     * @return integer type
     */
    public int getHorizontalDimension() { return bodyMap.getNumberOfColumns(); }

    /**
     * Returns vertical dimension of the map
     * @return integer type
     */
    public int getVerticalDimension() { return bodyMap.getNumberOfLines(); }

    /**
     * Adds elements at this point
     * @param elements, point
     * @throws InvalidMapException
     */
    private void addElements(Point point, String... elements) throws InvalidMapException
    {
        String elementsInString=new String();

        if(!pointExist(point)) throw new InvalidMapException();
        if(bodyMap.get(point.y, point.x).length()>0) elementsInString="__";

        for(int i=0; i<elements.length-1; i++) elementsInString+=(elements[i]+"__");

        elementsInString+=elements[elements.length-1];
        bodyMap.addElement(point.y, point.x, elementsInString);
    }

    /**
     * Adds biomes at this point
     * @param biomes
     * @throws InvalidMapException, DirectionException, ElementException
     */
    public void addBiomes(Element... biomes) throws InvalidMapException
    {
        int i=0;
        String all[]=new String[biomes.length];
        for(Element element : biomes) all[i++]=element.toString();
        addElements(drone, all);
    }

    /**
     * Adds a point of interest at this point
     * @param pointInterest, ids
     * @throws InvalidMapException, DirectionException, ElementException
     */
    private void addPointInterests(Element pointInterest, String... ids) throws InvalidMapException
    {
        for(int i=0; i<ids.length; i++) ids[i]=pointInterest+"__"+ids[i];
        addElements(drone, ids);
    }

    /**
     * Adds a creek at this point (the user has just to enter the id of the creek (or more if there are several creeks))
     * @param ids
     * @throws InvalidMapException, DirectionException, ElementException
     */
    public void addCreeks(String... ids) throws InvalidMapException { addPointInterests(Element.CREEK, ids); }

    /**
     * Adds the emergency site at this point (the user has just to enter the id of the emergency site)
     * @param id
     * @throws InvalidMapException, DirectionException, ElementException
     */
    public void addEmergencySite(String id) throws InvalidMapException
    {
        addElements(drone, Element.EMERGENCY_SITE.toString());
        emergencySiteId=id;
    }

    /**
     * get the emergency site id
     * @return String type, return new String() if the id is not known yet
     */
    public String getEmergencySiteId() { return emergencySiteId; }

    /**
     * get all the creek ids collected at this point
     * @param point
     * @return String[] type
     * @throws InvalidMapException
     */
    public String[] getCreekIds(Point point) throws InvalidMapException
    {
        String elements[]=bodyMap.get(point.y, point.x).split("__");
        List<String> ids=new ArrayList<>();

        for(int i=0; i<elements.length; i++)
        {
            try { Element.valueOf(elements[i]); }
            catch (IllegalArgumentException e) { ids.add(elements[i]); }
        }

        String idsInArray[] = new String[ids.size()];
        idsInArray = ids.toArray(idsInArray);

        return idsInArray;
    }

    /**
     * get all the biomes collected at this point
     * @param point
     * @return Element[] type
     * @throws InvalidMapException
     */
    public Element[] getBiomes(Point point) throws InvalidMapException
    {
        if(!pointExist(point)) throw new InvalidMapException();

        String elements[]=bodyMap.get(point.y, point.x).split("__");
        List<Element> biomes=new ArrayList<>();

        for(int i=0; i<elements.length; i++)
        {
            IllegalArgumentException exception=null;

            try { Element.valueOf(elements[i]); }
            catch (IllegalArgumentException e) { exception=e; }

            if(exception==null) biomes.add(Element.valueOf(elements[i]));
        }

        Element biomesInArray[] = new Element[biomes.size()];
        biomesInArray = biomes.toArray(biomesInArray);

        return biomesInArray;
    }

    /**
     * Deletes all the biomes occurrences at this point
     * @param point, element
     * @return boolean type, true if the biome has been deleted and false otherwise
     * @throws InvalidMapException
     */
    public boolean deleteBiome(Point point, Element element) throws InvalidMapException
    {
        if(!pointExist(point)) throw new InvalidMapException();

        boolean done=false;
        String biomes=bodyMap.get(point.y, point.x);

        if(biomes.contains(element.toString()))
        {
            String toDelete=new String();

            if(biomes.contains(element.toString()+"__")) toDelete=element.toString()+"__";
            else if(biomes.contains("__"+element.toString())) toDelete="__"+element.toString();
            else if(biomes.contains(element.toString())) toDelete=element.toString();

            bodyMap.setElement(point.y, point.x, biomes.replace(toDelete, ""));
            done=true;
        }

        return done;
    }

    /**
     * Returns if there is this element at this point
     * @param point, element
     * @return boolean type, true if there is the element, false otherwise
     * @throws InvalidMapException
     */
    public boolean hasElement(Point point, Element element) throws InvalidMapException
    {
        if(!pointExist(point)) throw new InvalidMapException();
        return bodyMap.get(point.y, point.x).contains(element.toString());
    }

    /**
     * Returns the number of element occurrences
     * @param element
     * @return integer type
     * @throws InvalidMapException
     */
    public int getNumberOfbiomes(Element element) throws InvalidMapException
    {
        int count=0;

        for(int i=0; i<bodyMap.getNumberOfLines(); i++)
            for(int j=0; j<bodyMap.getNumberOfColumns(); j++)
                for(Element k:getBiomes(new Point(j, i)))
                    if(element==k)
                        count++;

        return count;
    }

    /**
     * prints the current map
     */
    public void printStatement() throws InvalidMapException
    {
        for(int i=0; i<bodyMap.getNumberOfLines(); i++)
        {
            System.out.print("[\t");
            for(int j=0; j<bodyMap.getNumberOfColumns(); j++)
            {
                if(bodyMap.get(i, j).equals("")) System.out.print("UNKNOWN\t");
                else System.out.print(bodyMap.get(i, j) +"\t");
            }
            System.out.println("]");
        } System.out.println();
    }

}