package fr.unice.polytech.si3.qgl.iaad.islandMap;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Théo
 */

public class Creek {

    public List<List<Integer>> creeks;   //Une liste de couple qui contient les coordonnées de toutes les creeks


    public Creek()
    {
        creeks = new ArrayList<>();
    }

    /*
     * Ajoute manuellement les creeks à la liste
     */

    public void addCreek(int x, int y)
    {
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(x);
        temp.add(y);
        this.creeks.add(temp);
    }

    /*
     *Getter : renvoie la liste des creeks d'une map
     */

    public List<List<Integer>> getCreeks()
    {
        return this.creeks;
    }

    /*
    Getter : renvoie une creek à un indice particulier
     */

    public List<Integer> getOneCreek(int i)
    {
        return this.creeks.get(i);
    }
    /*
    * Parcourt la map et lorsqu'il y a une map sur une case, l'ajoute dans la liste des creeks
    */

    public void addAllTheCreeks(IslandMap m)
    {
        for (int j=0; j<m.getVerticalDimension(); j++)
        {
            for (int i=0; i<m.getHorizontalDimension(); i++)
            {
                if (m.hasElement(i,j, Element.CREEK))
                {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    creeks.add(temp);
                }
            }
        }

    }

    /*
    * Determines quelle creek est la plus proche du site d'urgence
     */

    public ArrayList<Integer> closest(double xSite, double ySite)
    {
        double X;
        double Y;
        double temp;
        double distance=-1;
        double min=1000000000; //pas beau mais pour le moment ça ira
        ArrayList<Integer> minCoordinates = new ArrayList<>();
        int xMin= creeks.get(0).get(0);
        int yMin = creeks.get(0).get(1);

        for (int i=0; i< creeks.size(); i++)
        {
            X = Math.pow(creeks.get(i).get(0)-xSite,2);
            Y = Math.pow(creeks.get(i).get(1)-ySite,2);
            temp =Math.abs(X+Y);
            distance = Math.pow(temp,0.5);
            if (distance < min)
            {
                min = distance;
                xMin= creeks.get(i).get(0);
                yMin = creeks.get(i).get(1);
            }
        }

        if (min!=1000000000)
        {
            minCoordinates.add(xMin);
            minCoordinates.add(yMin);
            return minCoordinates;
        }
        return null;
    }

}