package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.Point;

/**
 * @author romain
 * Created on 13/11/16.
 */
public class IslandMap
{
    private DynamicMatrix bodyMap;

    /*
     * Our location on the islandMap
     */
    private Point location;

    private EmergencySite emergencySite;

    /*
     * Saves what dimensions are finished in the islandMap
     */
    private boolean dimensionFinished[];

    private boolean groundMode;

    /**
     * Default constructor
     * Builds the body of the islandMap as an unique point with (0, 0) as coordinates
     * Sets the location at the point (0, 0)
     * Sets all dimensions as not finished
     * The emergency has an empty id and a null position
     */
    public IslandMap()  { this(new DynamicMatrix(), new Point(), new boolean[4], new EmergencySite()); }

    private IslandMap(DynamicMatrix bodyMap, Point location, boolean[] dimensionFinished, EmergencySite emergencySite)
    {
        this.bodyMap = bodyMap;
        this.location = location;
        this.dimensionFinished = dimensionFinished;
        this.emergencySite = emergencySite;
    }

    /**
     * Converts the aerial islandMap in land islandMap
     */
    boolean zoom()
    {
        boolean done = true;

        if(!groundMode)
        {
            IslandMap oldMap = new IslandMap(bodyMap, location, dimensionFinished, emergencySite);

            bodyMap = new DynamicMatrix(oldMap.bodyMap.getNumberOfLines()*3, oldMap.bodyMap.getNumberOfColumns()*3);

            try
            {
                for(int x=0, x_oldMap=0; x<getHorizontalDimension(); x+=3, x_oldMap++)
                {
                    for(int y=0, y_oldMap=0; y<getVerticalDimension(); y+=3, y_oldMap++)
                    {
                        for(int x_bloc=0; x_bloc<3; x_bloc++)
                        {
                            for(int y_bloc=0; y_bloc<3; y_bloc++)
                            {
                                bodyMap.getSquare(new Point(x+x_bloc, y+y_bloc)).copy(oldMap.bodyMap.getSquare(new Point(x_oldMap, y_oldMap)));
                            }
                        }
                    }
                }

                location = new Point(oldMap.location.x*3+1, oldMap.location.y*3+1);
                bodyMap.getSquare(location).setAsOldGroundLocation();
            }

            catch(InvalidMapException exception) { done = false; }

            groundMode = true;
        }

        return done;
    }

    /**
     * Informs if the point at coordinates(x, y) exists in the islandMap
     * @param point, any coordinates
     * @return boolean type : true if the point exist, false otherwise
     */
    boolean pointExist(Point point) { return bodyMap.pointExist(point); }

    /**
     * Receives a direction
     * Changes dimensions of the islandMap
     * Updates elements coordinates
     * Updates the location coordinates
     * @param direction, numberOfPoints
     */
    private void addPoints(Direction direction, int numberOfPoints)
    {
        numberOfPoints-=getNumberOfAvailablePoints(direction);

        switch(direction)
        {
            case N:
                bodyMap.addLines(0, numberOfPoints);
                location.move(location.x, location.y+numberOfPoints);
                break;
            case S:
                bodyMap.addLines(-1, numberOfPoints);
                break;
            case E:
                bodyMap.addColumns(-1, numberOfPoints);
                break;
            case W:
                bodyMap.addColumns(0, numberOfPoints);
                location.move(location.x+numberOfPoints, location.y);
                break;
        }
    }

    /**
     * Returns the location coordinates
     * @return Point type
     */
    public Point getLocation() { return location; }

    /**
     * new Fly(direction) in the islandMap
     * @param direction, the direction where the location has to moveLocation
     * @throws InvalidMapException, if the location moves on a point that does not exist
     */
    public void moveLocation(Direction direction) throws InvalidMapException
    {
        switch(direction)
        {
            case N:
                location.move(location.x, location.y-1);
                break;
            case S:
                location.move(location.x, location.y+1);
                break;
            case E:
                location.move(location.x+1, location.y);
                break;
            case W:
                location.move(location.x-1, location.y);
                break;
        }

        if(groundMode)
        {
            bodyMap.getSquare(location).setAsOldGroundLocation();
        }

        else
        {
            bodyMap.getSquare(location).setAsOldAerialLocation();
        }
    }

    public boolean isAnOldAerialLocation(Point point) throws InvalidMapException { return bodyMap.getSquare(point).isAnOldAerialLocation(); }

    public boolean isAnOldGroundLocation(Point point) throws InvalidMapException { return bodyMap.getSquare(point).isAnOldGroundLocation(); }

    /**
     * Informs if this direction is finished
     * @param direction, the direction to test
     * @return boolean type : true if the direction is finished, false otherwise
     */
    public boolean isDirectionFinished(Direction direction) { return dimensionFinished[direction.ordinal()]; }

    /**
     * Sets a direction as finished
     * Changes dimensions of the islandMap
     * Updates elements coordinates
     * Updates the location coordinates
     * @param direction, Direction type
     * @param numberOfPoints, Integer type
     */
    public void setOutOfRange(Direction direction, int numberOfPoints)
    {
        if(!dimensionFinished[direction.ordinal()])
        {
            addPoints(direction, numberOfPoints);
            dimensionFinished[direction.ordinal()] = true;
        }
    }

    /**
     * Informs if the islandMap is finished
     * @return boolean type, true if island Map Is Finished and false otherwise
     */
    public boolean isFinished()
    {
        boolean test = true;

        for(boolean currentDimensionFinished : dimensionFinished)
        {
            if(!currentDimensionFinished)
            {
                test = false;
                break;
            }
        }

        return test;
    }

    /**
     * Changes dimensions of the islandMap
     * Updates elements coordinates
     * Updates the location coordinates
     * Adds Element.GROUND at numberOfPoints+1 compared to the location location
     * @param direction, direction to set ground
     * @param numberOfPoints, number of points to add to islandMap
     */
    public boolean setGround(Direction direction, int numberOfPoints)
    {
        boolean done = true;

        numberOfPoints++;

        if(numberOfPoints>getNumberOfAvailablePoints(direction))
            if(!isDirectionFinished(direction))
                addPoints(direction, numberOfPoints);

        Point point = new Point();

        switch(direction)
        {
            case N:
                point = new Point(location.x, location.y-numberOfPoints);
                break;
            case S:
                point = new Point(location.x, location.y+numberOfPoints);
                break;
            case E:
                point = new Point(location.x+numberOfPoints, location.y);
                break;
            case W:
                point = new Point(location.x-numberOfPoints, location.y);
                break;
        }

        try { bodyMap.getSquare(point).addElement(Element.GROUND); }
        catch(InvalidMapException exception) { done = false; }

        return done;
    }

    /**
     * Returns the number of available points in this direction compared to the location location
     * @param direction, direction to test
     * @return integer type
     */
    public int getNumberOfAvailablePoints(Direction direction)
    {
        int distance = 0;

        switch(direction)
        {
            case N:
                distance = location.y;
                break;
            case S:
                distance = getVerticalDimension()- location.y-1;
                break;
            case E:
                distance = getHorizontalDimension()- location.x-1;
                break;
            case W:
                distance = location.x;
                break;
        }

        return distance;
    }

    public int getHorizontalDimension() { return bodyMap.getNumberOfColumns(); }

    public int getVerticalDimension() { return bodyMap.getNumberOfLines(); }

    public void addBiomes(Biome... biomes) throws InvalidMapException { bodyMap.getSquare(location).addBiomes(biomes); }

    public void addCreeks(String... ids) throws InvalidMapException { bodyMap.getSquare(location).addCreeks(ids); }

    public boolean addEmergencySite(String id)
    {
        boolean done = true;

        try
        {
            if(!emergencySite.isFound())
            {
                emergencySite = new EmergencySite(id);
                bodyMap.getSquare(location).addElement(Element.EMERGENCY_SITE);
            }

            else
            {
                done = false;
            }
        }

        catch(InvalidMapException exception) { done = false; }

        return done;
    }

    public String getEmergencySiteId() { return emergencySite.getId(); }

    public String[] getCreekIds(Point point) throws InvalidMapException { return bodyMap.getSquare(point).getCreekIds(); }

    public Biome[] getBiomes(Point point) throws InvalidMapException { return bodyMap.getSquare(point).getBiomes(); }

    public Element[] getElements(Point point) throws InvalidMapException { return bodyMap.getSquare(point).getElements(); }

    void deleteBiomes(Point point, Biome... biomes) throws InvalidMapException { bodyMap.getSquare(point).deleteBiomes(biomes); }

    boolean hasBiome(Point point, Biome biome) throws InvalidMapException
    {
        boolean test = false;

        for(Biome current : getBiomes(point))
        {
            if(current == biome)
            {
                test = true;
                break;
            }
        }

        return test;
    }

    boolean hasElement(Point point, Element element) throws InvalidMapException
    {
        boolean test = false;

        for(Element current : getElements(point))
        {
            if(current == element)
            {
                test = true;
                break;
            }
        }

        return test;
    }
}