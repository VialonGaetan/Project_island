package fr.unice.polytech.si3.qgl.iaad.islandMap;

/**
 * Created by romain on 15/11/16.
 */

/**
 * Matrix 2*2 of integers for maps
 */
public class DynamicTwoDimensionalMatrix
{
    /**
     * dimensions of the matrix
     */
    private int lines;
    private int cols;

    /**
     * Integer array
     */
    private int array[][];

    /**
     * default constructor
     * creates a matrix of only one element
     */
    public DynamicTwoDimensionalMatrix() { this(1, 1); }

    /**
     * constructor that creates a new matrix with specific dimensions
     * @param lines
     * @param cols
     */
    private DynamicTwoDimensionalMatrix(int lines, int cols)
    {
        this.lines=lines;
        this.cols=cols;
        array=new int[lines][cols];
    }

    /**
     * returns the number of lines
     * @return integer type
     */
    public int getNumberOfLines() { return lines; }

    /**
     * returns number of columns
     * @return integer type
     */
    public int getNumberOfColumns() { return cols; }

    /**
     * returns the element
     * suppose no index error
     * @return an integer
     */
    public int get(int line, int col) {	return array[line][col]; }

    /**
     * sets an element in the matrix
     * supposes no index error
     * @param line
     * @param col
     * @param value
     */
    public void set(int line, int col, int value)
    {
        if(line>=0 && line<array.length && col>=0 && col<array[0].length) array[line][col]=value;
        else System.err.println("Index out of matrix");
    }

    /**
     * this=another matrix
     * @param matrix
     */
    private void thisBecome(DynamicTwoDimensionalMatrix matrix)
    {
        lines=matrix.lines;
        cols=matrix.cols;
        array=new int[lines][cols];

        for(int i=0; i<lines; i++) for(int j=0; j<cols; j++) array[i][j]=matrix.get(i, j);
    }

    /**
     * adds the number of lines at the end or at the beginning of the matrix
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param position
     * @param lines
     */
    public void addLines(int position, int lines)
    {
        DynamicTwoDimensionalMatrix newMatrix=new DynamicTwoDimensionalMatrix(this.lines+lines, cols);

        if(position==0)
            for(int i=lines; i<(this.lines+lines); i++)	for(int j=0; j<cols; j++) newMatrix.set(i, j, array[i-lines][j]);

        else
            for(int i=0; i<this.lines; i++) for(int j=0; j<cols; j++) newMatrix.set(i, j, array[i][j]);

        thisBecome(newMatrix);
    }

    /**
     * add the number of columns at the end or at the beginning of the matrix
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param position
     * @param cols
     */
    public void addColumns(int position, int cols)
    {
        DynamicTwoDimensionalMatrix newMatrix=new DynamicTwoDimensionalMatrix(lines, this.cols+cols);

        if(position==0)
            for(int i=0; i<lines; i++) for(int j=cols; j<(this.cols+cols); j++)	newMatrix.set(i, j, array[i][j-cols]);

        else
            for(int i=0; i<lines; i++) for(int j=0; j<this.cols; j++) newMatrix.set(i, j, array[i][j]);

        thisBecome(newMatrix);
    }

    /**
     * prints all the matrix
     */
    public void print()
    {
        for(int i=0; i<lines; i++)
        {
            System.out.print("[\t");
            for(int j=0; j<cols; j++) System.out.print(""+array[i][j]+"\t");
            System.out.println("]");
        } System.out.println();
    }
}
