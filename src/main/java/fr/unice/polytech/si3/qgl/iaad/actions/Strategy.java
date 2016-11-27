package fr.unice.polytech.si3.qgl.iaad.actions;

/**
 * @author : Theo
 */


import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

import java.util.List;
import java.util.ArrayList;

public class Strategy {

    private static List<Object> actions;
    private IslandMap map;
    private Drone d;


    public Strategy(Drone d, IslandMap map)
    {
        this.map= map;
        this.d=d;
    }
    /*
    * Dans le but de scanner l'île,
    * L'idée est d'appliquer en boucle ces trois prochaines fonctions
     */

    /*
    * Ici, on scan à chaque tour
    * @return un scan
     */
    public Action echoIt()
    {
        return new Echo(d.direction);
    }

    /*
    * Methode permettant soit de poursuivre son chemin (Fly)
    * Soit de s'orienter afon de continuer à scanner l'île
    * Ici, j'ai dans un premier temps décider arbitrairement de tourner à gauche si jamais le drône croise de l'eau
    * (OUT OF RANGE)
    * @param : Le resultat d'une action
    * @return : Une action en conséquence
     */

    public Action setMyHeading(AreaResult result)
    {
        if (!result.getFound().equals("OUT_OF_RANGE"))
        {
            return new Fly();
        }
        else
        {
            return new Heading(this.d.direction.getLeft());
        }
    }

    /*
    *Troisième methode à appliquer :
    * Si le resultat de l'action précédente n'était pas OUT OF RANGE
    * cela signifie que nous sommes encore sur l'île, pour au moins un tour.
    * Dans ce cas, on scan. Sinon, on ne fait rien.
    * @param AreaResult
    * @return Action
     */
    public Action scanIt(AreaResult result)
    {
        if (!result.getFound().equals("OUT_OF_RANGE"))
        {
            return new Scan();
        }
        return new Echo(d.direction);
    }

}
