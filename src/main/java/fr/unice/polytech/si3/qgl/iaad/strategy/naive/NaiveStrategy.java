package fr.unice.polytech.si3.qgl.iaad.strategy.naive;

import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.protocol.Protocol;
import fr.unice.polytech.si3.qgl.iaad.protocol.StopExploration;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class NaiveStrategy implements Protocol
{
    private final Context context;

    public NaiveStrategy(Context context)
    {
        this.context = context;
    }

    @Override
    public Decision takeDecision()
    {
        return new Echo(context.getHeading());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return new StopExploration();
    }
}
