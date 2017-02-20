package fr.unice.polytech.si3.qgl.iaad.protocol;

import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Protocol
{
    Decision takeDecision();

    Protocol acknowledgeResults(Result result);
}
