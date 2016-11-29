package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public interface Protocol
{
    /**
     * @return l'action a effectué
     */
    Action nextAction();

    /**
     * @param result le résultat de l'action effectué
     * @return le nouveau protocole en vigueur
     */
    Protocol setResult(AreaResult result);
}
