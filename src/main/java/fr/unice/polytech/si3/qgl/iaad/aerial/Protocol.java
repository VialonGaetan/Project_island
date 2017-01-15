package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;

/**
 * Interface pour les protocoles.
 * Un protocol est une suite d'action qui renvoie un nouveau protocol en fonction des résultats récupérés.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
interface Protocol
{
    /**
     * @return l'action a effectué
     */
    Action nextAction() throws InvalidMapException;

    /**
     * @param result le résultat de l'action effectué
     * @return le nouveau protocole en vigueur
     */
    Protocol setResult(Area result) throws InvalidMapException;
}
