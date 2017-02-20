package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public interface ProtocolGround {

    /**
     * @return l'fr.unice.polytech.si3.qgl.iaad.action a effectué
     */
    Decision nextAction();

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return le nouveau protocole en vigueur
     */
    ProtocolGround setResult(Ground result);
}
