package fr.unice.polytech.si3.qgl.iaad.engine.format;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;

/**
 * convert some objects to string and the opposite
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Format
{
    /**
     * convert a string into a context
     * @param initialization, String value
     * @return Context object
     */
    Context stringToContext(String initialization);

    /**
     * convert a decision into a string
     * @param decision, Decision object
     * @return the decision into the string
     */
    String decisionToString(Decision decision);

    /**
     * convert a string into a result
     * @param result, String value
     * @return Result value
     */
    Result stringToResult(String result);
}
