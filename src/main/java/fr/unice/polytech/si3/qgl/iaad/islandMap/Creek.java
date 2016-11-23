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
    } //On verra plus tard pour l'ameliorer en tableau de longueur nopmbre de creek

    /*
     * Ajoute manuellement les creeks à la liste
     * @param x,y
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
     * @return la liste des coordonnées des creeks de la map
     */

    public List<List<Integer>> getCreeks()
    {
        return this.creeks;
    }

    /*
     *  Getter : renvoie une creek à un indice particulier
     *  @return une couple de coordonnées
     */

    public List<Integer> getOneCreek(int i)
    {
        return this.creeks.get(i);
    }

    /*
    * Parcourt la map et lorsqu'il y a une map sur une case, l'ajoute dans la liste des creeks
    */

    public void addAllTheCreeks(IslandMap m) throws AddPointsException
    {
        for (int j=0; j<m.getVerticalDimension(); j++)
        {
            for (int i=0; i<m.getHorizontalDimension(); i++)
                if (m.hasElement(i, j, Element.CREEK)) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    creeks.add(temp);
                }
        }

    }

    /*
    * Determine quelle creek est la plus proche du site d'urgence
    * @return un couple de coordonnées de la creek la plus proche du site d'urgence
     */

    public ArrayList<Integer> closest(double xSite, double ySite)
    {
        int xMin= creeks.get(0).get(0);
        int yMin = creeks.get(0).get(1);
        double min=Math.pow(Math.abs(Math.pow(xMin,2)+Math.pow(yMin,2)),0.5);
        ArrayList<Integer> minCoordinates = new ArrayList<>();
        minCoordinates.add(xMin); minCoordinates.add(yMin);
        double minInit = min;

        for (int i=0; i< creeks.size(); i++)
        {
            double X = Math.pow(creeks.get(i).get(0)-xSite,2);
            double Y = Math.pow(creeks.get(i).get(1)-ySite,2);
            double temp =Math.abs(X+Y);
            double distance = Math.pow(temp,0.5);
            if (distance < min)
            {
                min = distance;
                xMin= creeks.get(i).get(0);
                yMin = creeks.get(i).get(1);
            }
        }

        if (min!=minInit)
        {
            minCoordinates.add(xMin);
            minCoordinates.add(yMin);
            return minCoordinates;
        }
        return minCoordinates;
    }

}