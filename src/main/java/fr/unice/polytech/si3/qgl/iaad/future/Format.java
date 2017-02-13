package fr.unice.polytech.si3.qgl.iaad.future;

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
