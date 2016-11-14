package fr.unice.polytech.si3.qgl.iaad;

import java.lang.Math;


/**
 * Created by Théo on 13/11/2016.
 */

public class Creek {

    private static int[][] creeks = new int[100][2];
    /* Ici, on crée un teableau de tableau de longueur 2.
    En fait, cela représenterait un teableau dans lequel on vient placer les coordonnées [x,y] d'une creek dès qu'elle est trouvée
    dans la classe (méthode à implementer)
    La longueur est arbitraire : en pratique je ne pense pas qu'il y ait plus de 100 creeks
     */


    /*
    Ici on initialise tous les couples à l' Origine (qu'on matérialisera par [-1,-1] par exemple) [-1,-1] etc..
     */

    public Creek()
    {
        for (int i = 0; i<creeks.length; i++)
        {
            creeks[i][0] = -1;
            creeks[i][1] = -1;
        }
    }

    /* Methode permettant d'ajouter une creek à la liste des creeks trouvées.
    Typiquement : dans la classe drone il faudra implementer : Si on trouve une creek, on regarde à quelles coordonnées on est,
    et on fait un addCreek(x,y);
     */

    private void addCreek(int x, int y)
    {
        int i=0;
        while (creeks[i][0]!=-1) //on s'occupe seulement de regarder si la première coordonnées est != -1, sachant qu'elle vont de paire (x != -1 ==> y != -1)
        {
            i++;
        }
        creeks[i][0]=x; //on ajoute les creeks à la suite
        creeks[i][1]=y;
    }

    /*
    Ici, on calcule la creek la plus proche du site.
    On suppose que l'on connait les coordonnées du site (xSite, ySite)
    qu'on peut par exemple récupérer via une methode dans la classe Drone ou alors on peut créer une nouvelle
    classe Site
    */

    private int[] closest(int xSite, int ySite)
    {
        int i = 0;
        double X;
        double Y;
        double temp;
        double distance=-1;
        double min=1000000000; //pas beau mais pour le moment ça ira
        int[] minCoordinates = new int[2];

        while (creeks[i][0]!=-1) //on s'occupe seulement de regarder si la première coordonnées est !=-1, sachant qu'elle vont de paire (x !=1 ==> y !=-1)
        {
            X = Math.pow(creeks[i][0]-xSite,2);
            Y = Math.pow(creeks[i][1]-ySite,2);
            temp = Math.abs(X+Y);
            distance = Math.pow(temp,0.5);

            if (distance < min)
            {
                min=distance;
                minCoordinates[0]=creeks[i][0];
                minCoordinates[1]=creeks[i][1];
            }
            i++;
        }
        if (min!=-1)
        {
            return minCoordinates;
        }
        return null;
    }
    /**
    public static void main(String[] args) {
        Creek c = new Creek();
        c.addCreek(2,3);
        c.addCreek(4,5);
        System.out.println(c.closest(1,1));
        System.out.println("OK");
    }
    **/




}