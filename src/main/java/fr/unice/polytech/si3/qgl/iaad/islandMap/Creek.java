package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Théo
 */

public class Creek {

    private List<List<Integer>> creeks;   //Une liste de couple qui contient les coordonnées de toutes les creeks
    private IslandMap map;


    public Creek(IslandMap map) throws InvalidMapException {
        creeks = new ArrayList<>();
        this.map = map;
        addAllTheCreeks();
    } //On verra plus tard pour l'ameliorer en tableau de longueur nombre de creek

    /**
     * @param x
     * @param y
     * @return True if the creek is already known, False if not
     */
    public Boolean isKnown(int x, int y) {
        List<Integer> temp = new ArrayList<>();
        temp.add(x);
        temp.add(y);
        return this.creeks.contains(temp);
    }

    /**
     * Ajoute manuellement les creeks à la liste, à condition qu'elle ne soit pas déjà connu
     *
     * @param x,y
     */
    public void addCreek(int x, int y) {
        if (!isKnown(x, y)) {
            List<Integer> temp = new ArrayList<Integer>();
            temp.add(x);
            temp.add(y);
            this.creeks.add(temp);
        }
    }

    /**
     * Getter : renvoie la liste des creeks d'une map
     *
     * @return la liste des coordonnées des creeks de la map
     */

    public List<List<Integer>> getCreeks() {
        return this.creeks;
    }

    /**
     * Getter : renvoie une creek à un indice particulier
     *
     * @return une couple de coordonnées
     */

    public List<Integer> getOneCreek(int i) {
        return this.creeks.get(i);
    }

    /**
     * Parcourt la map et lorsqu'il y a une map sur une case, l'ajoute dans la liste des creeks
     */

    private void addAllTheCreeks() throws InvalidMapException {
        for (int j = 0; j < this.map.getVerticalDimension(); j++) {
            for (int i = 0; i < this.map.getHorizontalDimension(); i++)
                if (this.map.hasElement(new Point(j, i), Element.CREEK)) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    this.creeks.add(temp);
                }
        }
    }

    /**
     * Determine quelle creek est la plus proche du site d'urgence
     * Et retourne son ID
     * @return ID de la creek la plus proche du site d'urgence.
     */

    public String[] getClosestID(double xSite, double ySite) throws InvalidMapException {
        int xMin = this.creeks.get(0).get(0); //On initialise le xMin
        int yMin = this.creeks.get(0).get(1); //On initialise le yMin
        double min = Math.pow(Math.abs(Math.pow(xMin - xSite, 2) + Math.pow(yMin - ySite, 2)), 0.5); //On initialise la distance entre le site et la première creek

        for (int i = 0; i < creeks.size(); i++) {
            double X = Math.pow(creeks.get(i).get(0) - xSite, 2); //on calcule la norme projetée sur x
            double Y = Math.pow(creeks.get(i).get(1) - ySite, 2); // projection sur y
            double temp = Math.abs(X + Y); //valeur absolue au cas où
            double distance = Math.pow(temp, 0.5); //on calcule la norme 2 (euclidienne)
            if (distance < min) {
                min = distance; //Si la distance calculée est plus petite que le dernier MIN en mémoire, on considère cette distance comme le nouveau MIN
                xMin = creeks.get(i).get(0); //on rempli la liste avec les nouvelles coordonnées du MIN
                yMin = creeks.get(i).get(1);
            }
        }
        Point point = new Point(xMin, yMin);
        return map.getCreekIds(point);
    }


    /*
    Main d'exemple d'utilisation de Creek et EmergencySite, dans le but de retourner l'id la plus proche pour pouvoir Land.
    Dans l'hypothèse où IslandMap est complète
     */
    public static void main(String[] args) throws InvalidMapException {
        IslandMap map = new IslandMap(); //init Map
        Creek creek = new Creek(map); //init creek
        EmergencySite e = new EmergencySite(map); //init emergencySite
        creek.addAllTheCreeks(); //on ajoute toutes les creek recensées dans la Map dans une liste
        System.out.println(creek.getClosestID(e.getX(),e.getY()));
    }
}