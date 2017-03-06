package fr.unice.polytech.si3.qgl.iaad.strategy;

import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Protocol
{
    Decision takeDecision();

    Protocol acknowledgeResults(Result result);
}
