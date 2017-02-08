package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.Point;

/**
 * @author romain
 * Created on 15/11/16.
 */

class DynamicMatrix
{
    /**
     * Dimensions of the matrix
     */
    private int numberOfLines;
    private int numberOfColumns;

    /**
     * String array
     */
    private Square squares[][];

    /**
     * Default constructor
     * Creates a matrix of only one element
     */
    DynamicMatrix() { this(1, 1); }

    /**
     * Constructor that creates a new matrix with specific dimensions
     * @param numberOfLines, Integer type
     * @param numberOfColumns, Integer type
     */
    DynamicMatrix(int numberOfLines, int numberOfColumns)
    {
        this.numberOfLines = numberOfLines;
        this.numberOfColumns = numberOfColumns;
        squares = new Square[numberOfLines][numberOfColumns];

        for(int i=0; i<numberOfLines; i++)
            for(int j=0; j<numberOfColumns; j++)
                squares[i][j] = new Square();

        squares[0][0].setAsOldAerialLocation();
    }

    /**
     * Returns the number of numberOfLines
     * @return integer type
     */
    int getNumberOfLines() { return numberOfLines; }

    /**
     * Returns number of columns
     * @return integer type
     */
    int getNumberOfColumns() { return numberOfColumns; }

    /**
     * Returns the string with these index
     * @return String type
     * @param point, a point
     * @throws InvalidMapException, if the point does not exist
     */
    Square getSquare(Point point) throws InvalidMapException
    {
        if(!pointExist(point))
        {
            throw new InvalidMapException();
        }

        return squares[point.y][point.x];
    }

    /**
     * Informs if the point with these index exists in the matrix
     * @param point, a point
     * @return boolean type : true if the point exist, false otherwise
     */
    boolean pointExist(Point point) { return (point.y>=0 && point.y<squares.length && point.x>=0 && point.x<squares[0].length); }

    /**
     * this = another dynamicMatrix
     * @param dynamicMatrix, DynamicMatrix type
     */
    private void thisBecome(DynamicMatrix dynamicMatrix)
    {
        numberOfLines = dynamicMatrix.numberOfLines;
        numberOfColumns = dynamicMatrix.numberOfColumns;
        squares = new Square[numberOfLines][numberOfColumns];
        System.arraycopy(dynamicMatrix.squares, 0, squares, 0, numberOfLines);
    }

    /**
     * Adds the number of numberOfLines at the end or at the beginning of the matrix
     * Does not remove all the existing elements
     * Changes the matrix dimensions
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param position, Integer type
     * @param numberOfLines, Integer type
     */
    void addLines(int position, int numberOfLines)
    {
        DynamicMatrix newMatrix = new DynamicMatrix(this.numberOfLines+numberOfLines, numberOfColumns);

        if(position==0)
        {
            for(int i = numberOfLines; i<(this.numberOfLines+numberOfLines); i++)
            {
                for(int j = 0; j<numberOfColumns; j++)
                {
                    newMatrix.squares[i][j] = squares[i-numberOfLines][j];
                }
            }
        }

        else
        {
            for(int i = 0; i<this.numberOfLines; i++)
            {
                for(int j = 0; j<numberOfColumns; j++)
                {
                    newMatrix.squares[i][j] = squares[i][j];
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
     * @param position, Integer type
     * @param numberOfColumns, Integer type
     */
    void addColumns(int position, int numberOfColumns)
    {
        DynamicMatrix newMatrix = new DynamicMatrix(numberOfLines, this.numberOfColumns+numberOfColumns);

        if(position==0)
        {
            for(int i = 0; i< numberOfLines; i++)
            {
                for(int j = numberOfColumns; j<(this.numberOfColumns+numberOfColumns); j++)
                {
                    newMatrix.squares[i][j] = squares[i][j - numberOfColumns];
                }
            }
        }

        else
        {
            for(int i = 0; i< numberOfLines; i++)
            {
                for(int j = 0; j<this.numberOfColumns; j++)
                {
                    newMatrix.squares[i][j] = squares[i][j];
                }
            }

        }

        thisBecome(newMatrix);
    }
}
