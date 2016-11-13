package fr.unice.polytech.qgl.iaad;

/**
 * Created by romain on 13/11/16.
 */
public class Map {
    int verticalDimension;
    int horizontalDimension;

    private String cells[][]; //cells[horizontalDimension][verticalDimension]

    public Map(int verticalDimension, int horizontalDimension){
        this.horizontalDimension=horizontalDimension;
        this.verticalDimension=verticalDimension;
        cells=new String[horizontalDimension][verticalDimension];
    }

    public void setResource(int coordinateX, int coordinateY, String resource) {
        if(coordinateX>=horizontalDimension || coordinateY>=verticalDimension) System.err.println("CoordinateS out of range");
        cells[coordinateX][coordinateY]=resource;
    }

    public String getResource(int coordinateX, int coordinateY) {
        if(coordinateX>=horizontalDimension || coordinateY>=verticalDimension) System.err.println("CoordinateS out of range");
        return cells[coordinateX][coordinateY];
    }
}

