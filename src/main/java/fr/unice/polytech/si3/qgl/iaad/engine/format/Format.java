package fr.unice.polytech.si3.qgl.iaad.engine.format;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Format
{
    Context stringToContext(String initialization);

    String decisionToString(Decision decision);

    Result stringToResult(String result);
}
