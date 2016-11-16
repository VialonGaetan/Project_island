package fr.unice.polytech.si3.qgl.iaad.IslandMap;


import fr.unice.polytech.si3.qgl.iaad.Element;
import fr.unice.polytech.si3.qgl.iaad.Direction;

/**
 * Created by romain on 13/11/16.
 */

/**
 * Build a map from every movements completed
 * Contains all resources found
 */

public class IslandMap
{
    /**
     * matrix is the map
     */
    private DynamicTwoDimensionalMatrix matrix;

    /**
     * boolean that equals true if the map is finished and false otherwise
     */
    private boolean islandMapIsFinished;

    /**
     * coordinates of the starting point of the drone
     */
    private int[] coordinatesStartDrone;

    /**
     * Default constructor
     * @param matrix
     */
    public IslandMap()
    {
        matrix=new DynamicTwoDimensionalMatrix();
        coordinatesStartDrone=new int[2];
    }

    /**
     * set if the map is finished
     * @param islandMapIsFinished
     */
    public void setFinishedMap() { islandMapIsFinished=true; }

    /**
     * get if the map is finished
     * @return true if island Map Is Finished and false otherwise
     */
    public boolean isFinished() { return islandMapIsFinished; }

    /**
     * get the starting coordinates of the drone
     * @return int[] ie [x,y]
     */
    public int[] getStartingCoordinatesOfDrone() { return coordinatesStartDrone; }

    /**
     * get horizontal dimension of the map
     * @return an integer
     */
    public int getHorizontalDimension() { return matrix.getNumberOfColumns(); }

    /**
     * get vertical dimension of the map
     * @return an integer
     */
    public int getVerticalDimension() { return matrix.getNumberOfLines(); }

    /**
     * receive a direction
     * add points in the map
     * each time a point is created, set OCEAN as default resource
     * @param matrix
     */
    public void addPoints(Direction direction, int numberOfpoints)
    {
        switch(direction)
        {
            case N:
                matrix.addLines(0, numberOfpoints);
                coordinatesStartDrone[1]+=numberOfpoints;
                break;
            case S:
                matrix.addLines(-1, numberOfpoints);
                break;
            case E:
                matrix.addColumns(-1, numberOfpoints);
                break;
            case W:
                matrix.addColumns(-1, numberOfpoints);
                coordinatesStartDrone[0]+=numberOfpoints;
                break;
        }
    }

    /**
     * set an element at a the coordinates of a point
     * stop the program if bad coordinates and print an error message
     * @param matrix
     */
    public void setElement(int x, int y, Element element)
    {
        if(x>=0 && x<getHorizontalDimension() && y>=0 && y<getVerticalDimension()) matrix.set(y, x, element.ordinal());
        else System.err.println("1 Coordinates out of range");
    }

    /**
     * get an element at a the coordinates of a point
     * stop the program if bad coordinates and print an error message
     * @return element found
     */
    public Element getElement(int x, int y)
    {
        Element element=Element.NEW_ELEMENT;

        if(x>=0 && x<getHorizontalDimension() && y>=0 && y<getVerticalDimension())
        {
            for(Element currentElement:Element.values())
                if(currentElement.ordinal()==matrix.get(y, x))
                    element=currentElement;
        }

        if(element==Element.NEW_ELEMENT) System.err.println("Coordinates out of range");

        return element;
    }

    /**
     * check if there is an element at point coordinates
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

        else System.err.println("Coordinates out of range");

        return test;
    }

    /**
     * @return an integer of the number of occurrences of the element in the map
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
     * print the current map
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