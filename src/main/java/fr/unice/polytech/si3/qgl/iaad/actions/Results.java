package fr.unice.polytech.si3.qgl.iaad.actions;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public interface Results {



    /**
     * Recupere le cout d'une fr.unice.polytech.si3.qgl.iaad.action
     * @return fr.unice.polytech.si3.qgl.iaad.action cost
     */
    int getCost();

    /**
     * Permet de savoir si l'fr.unice.polytech.si3.qgl.iaad.action s'est bien passé
     * @return if no problem OK else KO
     */
    String getStatus();
}
