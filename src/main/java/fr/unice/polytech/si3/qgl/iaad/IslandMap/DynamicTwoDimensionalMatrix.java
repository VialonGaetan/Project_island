package fr.unice.polytech.si3.qgl.iaad.IslandMap;

/**
 * Created by romain on 15/11/16.
 */

/*
 * Matrix 2*2 of integers for maps
 */
public class DynamicTwoDimensionalMatrix
{
    /*
     * dimensions of the matrix
     */
    private int lines;
    private int cols;

    /*
     * Integer array
     */
    private int array[][];

    /*
     * default constructor
     * create a matrix of only one element
     * @param matrix
     */
    public DynamicTwoDimensionalMatrix() { this(1,1); }

    /*
     * constructor to create a null matrix with specific dimensions
     */
    private DynamicTwoDimensionalMatrix(int lines, int cols)
    {
        this.lines=lines;
        this.cols=cols;
        array=new int[lines][cols];
        for(int i=0; i<lines; i++) for(int j=0; j<cols; j++) array[i][j]=0;
    }
}
