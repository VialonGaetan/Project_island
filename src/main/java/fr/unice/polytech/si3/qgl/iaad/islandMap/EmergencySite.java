package fr.unice.polytech.si3.qgl.iaad.islandMap;

/**
 * @author Théo
 */
public class EmergencySite {

    public int[] coordinates = new int[2];

    public EmergencySite(int x, int y)
    {
        this.coordinates[0]=x;
        this.coordinates[1]=y;
    }

    public int[] getCoordinates()
    {
        return this.coordinates;
    }

    public int getX()
    {
        return this.coordinates[0];
    }

    public int getY()
    {
        return this.coordinates[1];
    }

    public void setX(int x)
    {
        this.coordinates[0]=x;
    }

    public void setY(int y)
    {
        this.coordinates[1]=y;
    }

    public void FindSite(IslandMap m)
    {
        //In progress
    }
}
