package fr.unice.polytech.si3.qgl.iaad.protocol;

import fr.unice.polytech.si3.qgl.iaad.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public class StopExploration implements Protocol
{
    @Override
    public Decision takeDecision()
    {
        return new Stop();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return null;
    }
}
