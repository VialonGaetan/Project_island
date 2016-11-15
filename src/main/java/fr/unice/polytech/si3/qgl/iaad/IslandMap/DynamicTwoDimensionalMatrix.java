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

    /*
     * get number of lines
     * @return an integer
     */
    public int getNumberOfLines() { return lines; }

    /*
     * get number of columns
     * @return an integer
     */
    public int getNumberOfColumns() { return cols; }

    /*
     * get an element of the matrix
     * suppose no index error
     * @return an integer
     */
    public int get(int line, int col) {	return array[line][col]; }

    /*
     * set an element in the matrix
     * suppose no index error
     * @param matrix
     */
    public void set(int line, int col, int value) {	array[line][col]=value; }

    /*
     * @this matrix=another matrix
     */
    private void thisBecome(DynamicTwoDimensionalMatrix matrix)
    {
        lines=matrix.lines;
        cols=matrix.cols;
        array=new int[lines][cols];

        for(int i=0; i<lines; i++) for(int j=0; j<cols; j++) array[i][j]=matrix.get(i, j);
    }

    /*
     * add the number of lines at the end or at the beginning of the matrix
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param matrix
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

    /*
     * add the number of columns at the end or at the beginning of the matrix
     * if position=0 then added at the beginning
     * if position!=0 then added at the end
     * @param matrix
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

    /*
     * print all the matrix
     */
    public void print()
    {
        for(int i=0; i<lines; i++)
        {
            System.out.print("[\t");
            for(int j=0; j<cols; j++) System.out.print(""+array[i][j]+"\t");
            System.out.println("]");
        }

        System.out.println();
    }
}
