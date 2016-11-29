package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */

public abstract class Area implements Action {

    public Direction direction;
    public AreaResult results;
    public String ID;
    public int people;
    public String rapport = "Nous allons être riche !!";

    /**
     * Lorsqu'une action est effectué créé un objet Result correspondant à l'action
     * @param result de l'explorer
     * @return objet resultat avec les données
     */
    public abstract AreaResult getResults(String result);

    /**
     * Permet de retourner le emergency site et la creek la plus proche s'il a trouvé
     * @return emergency site and creek ID
     */
    public String getRapport()
    {
        return rapport;
    }
}
