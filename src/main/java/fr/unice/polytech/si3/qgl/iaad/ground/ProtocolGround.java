package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public interface ProtocolGround {

    /**
     * @return l'action a effectué
     */
    Action nextAction() throws InvalidMapException;

    /**
     * @param result le résultat de l'action effectué
     * @return le nouveau protocole en vigueur
     */
    ProtocolGround setResult(Ground result) throws InvalidMapException;
}
