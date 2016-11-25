package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import java.awt.Point;

/**
 * Created by romain on 13/11/16.
 */

/**
 * Build a map from every movements completed
 * Contains all resources found
 * Manage the drone coordinates
 * Manage setOutOfRange
 * Manage ground
 */

public class IslandMap
{
    /**
     * matrix is the map represented by integers
     */
    private DynamicTwoDimensionalMatrix matrix;

    /**
     * current coordinates of the the drone
     * i=0 <=> y ; i=1 <=> x
     */
    private int[] droneCoordinates;

    /**
     * dimensionFinished is an array of booleans that inform if a direction is finished
     * i=0 => N ; i=1 => E ; i=2 => S ; i=3 => W (but that depends of the order in the enumeration)
     */
    private boolean dimensionFinished[];

    /**
     * Default constructor
     * initializes matrix as an unique point with (0, 0) as coordinates
     * initializes the coordinates of the drone at (0, 0)
     * initializes all the dimension as not finished
     */
    public IslandMap()
    {
        matrix = new DynamicTwoDimensionalMatrix();
        droneCoordinates = new int[2];
        dimensionFinished = new boolean[4];
    }

    /**
     * informs if the point at coordinates(line, column) exists
     * @param i
     * @param j
     * @return boolean type : true if the point exist, false otherwise
     */
    private boolean pointExist(int i, int j)
    {
        boolean test=false;

        if(i>=0 && i<matrix.getNumberOfLines() && j>=0 && j<matrix.getNumberOfColumns()) test=true;

        return test;
    }

    /**
     * receives a direction
     * adds points asked if the direction is unlocked in the map
     * each time a point is created, set UNKNOWN as default resource
     * the drone coordinates are updated
     * throw an exception if direction is finished
     * throw an exception if the number of points is inferior than the number of points available
     * @param direction
     * @param numberOfPoints
     * @throws AddPointsException
     */
    private void addPoints(Direction direction, int numberOfPoints)
    {
        if(isDirectionFinished(direction)) System.err.println("Erreur");;
        if(numberOfPoints<getNumberOfAvailablePoints(direction)) System.err.println("Erreur");;

        numberOfPoints-=getNumberOfAvailablePoints(direction);

        switch(direction)
        {
            case N:
                matrix.addLines(0, numberOfPoints);
                droneCoordinates[0]+=numberOfPoints;
                break;
            case S:
                matrix.addLines(-1, numberOfPoints);
                break;
            case E:
                matrix.addColumns(-1, numberOfPoints);
                break;
            case W:
                matrix.addColumns(0, numberOfPoints);
                droneCoordinates[1]+=numberOfPoints;
                break;
        }
    }

    /**
     * returns drone coordinates
     * @return Point type
     */
    public Point getDroneCoordinates() { return new Point(droneCoordinates[1], droneCoordinates[0]); }

    /**
     * moves the drone just by one point (update its coordinates)
     * @param direction
     * @return boolean type, true if the drone is still in the map and false it it left the map
     */
    public boolean moveDroneCorrectly(Direction direction)
    {
        boolean isAuthorizedTomove=false;

        switch(direction)
        {
            case N:
                if(pointExist(droneCoordinates[0]-1, droneCoordinates[1])) isAuthorizedTomove=true;
                droneCoordinates[0]--;
                break;
            case S:
                if(pointExist(droneCoordinates[0]+1, droneCoordinates[1])) isAuthorizedTomove=true;
                droneCoordinates[0]++;
                break;
            case E:
                if(pointExist(droneCoordinates[0], droneCoordinates[1]+1)) isAuthorizedTomove=true;
                droneCoordinates[1]++;
                break;
            case W:
                if(pointExist(droneCoordinates[0], droneCoordinates[1]-1)) isAuthorizedTomove=true;
                droneCoordinates[1]--;
                break;
        }

        return isAuthorizedTomove;
    }

    /**
     * informs if this direction is finished
     * @param direction
     * @return boolean type : true if the direction is finished, false otherwise
     */
    public boolean isDirectionFinished(Direction direction) { return dimensionFinished[direction.ordinal()]; }

    /**
     * sets the direction as finished
     * adds points in function of the drone location
     * throws an exception is the points couldn't be added
     * @param direction
     * @param numberOfPoints
     * @throws AddPointsException
     */
    public void setOutOfRange(Direction direction, int numberOfPoints)
    {
        addPoints(direction, numberOfPoints);
        dimensionFinished[direction.ordinal()]=true;
    }

    /**
     * informs if the map is finished
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
     * adds points in function of the drone location plus 1 that contains ground element
     * throws an exception is the points couldn't be added
     * @param direction
     * @param numberOfPoints
     * @throws AddPointsException
     */
    public void ground(Direction direction, int numberOfPoints)
    {
        numberOfPoints++;

        if(!isDirectionFinished(direction)) addPoints(direction, numberOfPoints);

        switch(direction)
        {
            case N:
                matrix.set(droneCoordinates[0]-numberOfPoints, droneCoordinates[1], Element.GROUND.ordinal());
                break;
            case S:
                matrix.set(droneCoordinates[0]+numberOfPoints, droneCoordinates[1], Element.GROUND.ordinal());
                break;
            case E:
                matrix.set(droneCoordinates[0], droneCoordinates[1]+numberOfPoints, Element.GROUND.ordinal());
                break;
            case W:
                matrix.set(droneCoordinates[0], droneCoordinates[1]-numberOfPoints, Element.GROUND.ordinal());
                break;
        }
    }

    /**
     * returns the number of available points
     * @param direction
     * @return integer type
     */
    public int getNumberOfAvailablePoints(Direction direction)
    {
        int distance=0;

        switch(direction)
        {
            case N:
                distance=droneCoordinates[0];
                break;
            case S:
                distance=getVerticalDimension()-droneCoordinates[0]-1;
                break;
            case E:
                distance=getHorizontalDimension()-droneCoordinates[1]-1;
                break;
            case W:
                distance=droneCoordinates[1];
                break;
        }

        return distance;
    }

    /**
     * returns horizontal dimension of the map
     * @return an integer
     */
    public int getHorizontalDimension() { return matrix.getNumberOfColumns(); }

    /**
     * returns vertical dimension of the map
     * @return an integer
     */
    public int getVerticalDimension() { return matrix.getNumberOfLines(); }

    /**
     * sets an element at the Drone coordinates
     * stops the program if bad coordinates and print an error message
     * @param element
     */
    public void setElement(Element element) { matrix.set(droneCoordinates[0], droneCoordinates[1], element.ordinal()); }

    /**
     * returns the element at this point the coordinates
     * stops the program if bad coordinates and print an error message
     * @return element found
     */
    public Element getElement(int x, int y)
    {
        if(x>=0 && x<getHorizontalDimension() && y>=0 && y<getVerticalDimension())
        {
            for(Element currentElement:Element.values())
                if(currentElement.ordinal()==matrix.get(y, x))
                    return currentElement;
        }

        else System.err.println("Erreur");;

        return null;
    }

    /**
     * checks if there is an element at point coordinates
     * @return true if there is the element
     * @return false otherwise
     */
    public boolean hasElement(int x, int y, Element element)
    {
        boolean test=false;

        if((x>=0 && x<getHorizontalDimension()) && (y>=0 && y<getVerticalDimension()))
        {
            if(element==getElement(x, y))
                test=true;
        }

        else System.err.println("Erreur");

        return test;
    }

    /**
     * returns the number of occurrences of the element
     * @return integer type
     */
    public int numberOfOccurrencesOfTheElement(Element element)
    {
        int count=0;

        for(int i=0; i<matrix.getNumberOfLines(); i++)
            for(int j=0; j<matrix.getNumberOfColumns(); j++)
                if(hasElement(j, i, element))
                    count++;

        return count;
    }

    /**
     * prints the current map
     */
    public void printStatement()
    {
        for(int i=0; i<matrix.getNumberOfLines(); i++)
        {
            System.out.print("[\t");

            for(int j=0; j<matrix.getNumberOfColumns(); j++)
                for(Element currentElement:Element.values())
                    if(currentElement.ordinal()==matrix.get(i, j)) System.out.print(""+ currentElement.toString() +"\t");

            System.out.println("]");
        }

        System.out.println();
    }
}