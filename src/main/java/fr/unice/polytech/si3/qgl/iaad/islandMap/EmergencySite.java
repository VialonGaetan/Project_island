package fr.unice.polytech.si3.qgl.iaad.islandMap;

import java.util.ArrayList;

/**
 * @author Théo
 */
public class EmergencySite {

    public int[] coordinates = new int[2];
    private IslandMap map;

    public EmergencySite(int x, int y, IslandMap map)
    {
        this.coordinates[0]=x;
        this.coordinates[1]=y;
        this.map = map;
    }

    /**
    * Permet de savoir si la case pointée est un site d'urgence
    *@param : Coordonnées d'une case
    * @return : True or False
    */
    public Boolean isASite(int x, int y)
    {
        if (this.map.hasElement(x, y, Element.EMERGENCY_SITE))
        {
            return true;
        }
        return false;
    }

    /**
    * Permet de retourner les coordonnées du site d'urgence
    * @return : tableau de 2 contenant les coordonnées du site
     */
    public int[] getCoordinates()
    {
        return this.coordinates;
    }

    /**
    * @return : l'abscisse du site
     */
    public int getX()
    {
        return this.coordinates[0];
    }

    /**
    *@return : l'ordonnée du site
     */
    public int getY()
    {
        return this.coordinates[1];
    }

    /**
    * Assigne l'abscisse du site manuellement
    * @param : un entier x
     */
    public void setX(int x)
    {
        this.coordinates[0]=x;
    }

    /**
     * Assigne manuellement l'ordonnée du site
     * @param y
     */
    public void setY(int y)
    {
        this.coordinates[1]=y;
    }

    /**
     * Permet de parcourir une IslandMap et d'y trouver automatiquement le site d'urgence si il est entrée dans l'IslandMap
     * @return : un tableau contenant les coordonnées du site
     */
    public int[] FindSite()
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
