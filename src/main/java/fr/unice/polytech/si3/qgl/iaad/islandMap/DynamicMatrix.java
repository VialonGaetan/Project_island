package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

/**
 * Created by romain on 15/11/16.
 */

/**
 * Dynamic Matrix that contains String elements
 */
public class DynamicMatrix
{
    /**
     * Dimensions of the matrix
     */
    private int numberOfLines, numberOfColumns;

    /**
     * String array
     */
    private String array[][];

    /**
     * Default constructor
     * Creates a matrix of only one element
     */
    public DynamicMatrix() { this(1, 1); }

    /**
     * Constructor that creates a new matrix with specific dimensions
     * @param numberOfLines
     * @param numberOfColumns
     */
    private DynamicMatrix(int numberOfLines, int numberOfColumns)
    {
        this.numberOfLines =numberOfLines;
        this.numberOfColumns=numberOfColumns;
        array=new String[numberOfLines][numberOfColumns];

        for(int i=0; i<numberOfLines; i++)
            for(int j=0; j<numberOfColumns; j++)
                array[i][j]=new String();
    }

    /**
     * Returns the number of numberOfLines
     * @return integer type
     */
    public int getNumberOfLines() { return numberOfLines; }

    /**
     * Returns number of columns
     * @return integer type
     */
    public int getNumberOfColumns() { return numberOfColumns; }

    /**
     * Returns the string with these index
     * @return String type
     * @param line, column
     * @throws InvalidMapException
     */
    public String get(int line, int column) throws InvalidMapException
    {
        if(!pointExist(line, column)) throw new InvalidMapException();
        return array[line][column];
    }

    /**
     * Informs if the point with these index exists in the matrix
     * @param line, column
     * @return boolean type : true if the point exist, false otherwise
     */
    public boolean pointExist(int line, int column)
    {
        boolean test=false;

        if(line>=0 && line<array.length && column>=0 && column<array[0].length)
            test=true;

        return test;
    }

    /**
     * Adds an element at this point
     * @param line, column, value
     * @throws InvalidMapException
     */
    public void addElement(int line, int column, String value)  throws InvalidMapException
    {
        if(!pointExist(line, column)) throw new InvalidMapException();
        array[line][column]+=value;
    }

    /**
     * Sets an element at this point
     * @param line, col, value
     * @throws InvalidMapException
     */
    public void setElement(int line, int col, String value) throws InvalidMapException
    {
        if(!pointExist(line, col)) throw new InvalidMapException();
        array[line][col]=value;
    }

    /**
     * this = another dynamicMatrix
     * @param dynamicMatrix
     */
    private void thisBecome(DynamicMatrix dynamicMatrix)
    {
        numberOfLines =dynamicMatrix.numberOfLines;
        numberOfColumns=dynamicMatrix.numberOfColumns;
        array=new String[numberOfLines][numberOfColumns];

        System.arraycopy(dynamicMatrix.array, 0, array, 0, numberOfLines);
    }

    /**
     * Adds the number of numberOfLines at the end or at the beginning of the matrix
     * Does not remove all the existing elements
     * Changes the matrix dimensions
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param position, numberOfLines
     * @throws InvalidMapException
     */
    public void addLines(int position, int numberOfLines)
    {
        DynamicMatrix newMatrix=new DynamicMatrix(this.numberOfLines +numberOfLines, numberOfColumns);

        if(position==0)
        {
            for(int i = numberOfLines; i<(this.numberOfLines +numberOfLines); i++)
            {
                for(int j = 0; j<numberOfColumns; j++)
                {
                    try { newMatrix.setElement(i, j, array[i-numberOfLines][j]); }
                    catch (InvalidMapException e) { e.printStackTrace(); }
                }
            }
        }

        else
        {
            for(int i = 0; i<this.numberOfLines; i++)
            {
                for(int j = 0; j<numberOfColumns; j++)
                {
                    try { newMatrix.setElement(i, j, array[i][j]); }
                    catch (InvalidMapException e) { e.printStackTrace(); }
                }
            }
        }

        thisBecome(newMatrix);
    }

    /**
     * Adds the number of columns at the end or at the beginning of the matrix
     * Does not remove all the existing elements
     * Changes the matrix dimensions
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param position, numberOfLines
     * @throws InvalidMapException
     */
    public void addColumns(int position, int numberOfColumns)
    {
        DynamicMatrix newMatrix=new DynamicMatrix(numberOfLines, this.numberOfColumns+numberOfColumns);

        if(position==0)
        {
            for(int i = 0; i< numberOfLines; i++)
            {
                for(int j = numberOfColumns; j<(this.numberOfColumns+numberOfColumns); j++)
                {
                    try { newMatrix.setElement(i, j, array[i][j-numberOfColumns]); }
                    catch (InvalidMapException e) { e.printStackTrace(); }
                }
            }
        }

        else
        {
            for(int i = 0; i< numberOfLines; i++)
            {
                for(int j = 0; j<this.numberOfColumns; j++)
                {
                    try { newMatrix.setElement(i, j, array[i][j]); }
                    catch (InvalidMapException e) { e.printStackTrace(); }
                }
            }

        }

        thisBecome(newMatrix);
    }
}
