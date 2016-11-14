package fr.unice.polytech.si3.qgl.iaad;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Théo
 */

public class Creek {

    public List<List<Double>> creeks;   //Une liste de couple qui contient les coordonnées de toutes les creeks


    public Creek()
    {
        creeks=new ArrayList<>();
    }
    /*
    * Ajoute manuellement les creeks à la liste
    * /
     */

    private void addCreek(int x, int y)
    {
        List<Double> temp = new ArrayList<Double>();
        temp.add((double) x);
        temp.add((double) y);
        this.creeks.add(temp);
    }

    /*
    * Parcourt la map et lorsqu'il y a une map sur une case, l'ajoute dans la liste des creeks
    */

    private void addAllTheCreeks(Map m)
    {
        //En attente de Map
    }

    /*
    * Determines quelle creek est la plus proche du site d'urgence
     */


    private List<Double> closest(double xSite, double ySite)
    {
        double X;
        double Y;
        double temp;
        double distance=-1;
        double min=1000000000; //pas beau mais pour le moment ça ira
        List<Double> minCoordinates = new ArrayList<Double>();
        double xMin= creeks.get(0).get(0);
        double yMin = creeks.get(0).get(1);

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