package fr.unice.polytech.si3.qgl.iaad.actions;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public interface Results {



    /**
     * Recupere le cout d'une action
     * @return action cost
     */
    int getCost();

    /**
     * Permet de savoir si l'action s'est bien passé
     * @return if no problem OK else KO
     */
    String getStatus();
}
