package fr.unice.polytech.si3.qgl.iaad.islandMap;

import java.util.ArrayList;

/**
 * @author Théo
 */
public class EmergencySite {

    public int[] coordinates = new int[2];
    IslandMap map;

    public EmergencySite(int x, int y, IslandMap map)
    {
        this.coordinates[0]=x;
        this.coordinates[1]=y;
        this.map = map;
    }

    public Boolean isASite(int x, int y) throws AddPointsException
    {
        if (this.map.hasElement(x, y, Element.EMERGENCY_SITE))
        {
            return true;
        }
        return false;
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

    public int[] FindSite() throws AddPointsException
    {
        for (int j=0; j<this.map.getVerticalDimension(); j++)
        {
            for (int i=0; i<this.map.getHorizontalDimension(); i++)
                if (this.map.hasElement(i, j, Element.EMERGENCY_SITE)) {
                    this.coordinates[0]=i;
                    this.coordinates[1]=j;
                    return this.coordinates;
                }
        }
        return null;
    }
}
