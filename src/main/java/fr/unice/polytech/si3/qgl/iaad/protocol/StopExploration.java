package fr.unice.polytech.si3.qgl.iaad.protocol;

import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.future.Decision;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public class StopExploration implements Protocol
{
    @Override
    public Decision takeDecision()
    {
        return null;
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return null;
    }
}
