package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;

/**
 * Interface pour les protocoles.
 * Un protocol est une suite d'fr.unice.polytech.si3.qgl.iaad.action qui renvoie un nouveau protocol en fonction des résultats récupérés.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
interface Protocol
{
    /**
     * @return l'fr.unice.polytech.si3.qgl.iaad.action a effectué
     */
    Decision nextAction() throws InvalidMapException;

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return le nouveau protocole en vigueur
     */
    Protocol setResult(Area result) throws InvalidMapException;
}
