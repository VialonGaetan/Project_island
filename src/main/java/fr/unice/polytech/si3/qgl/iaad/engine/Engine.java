package fr.unice.polytech.si3.qgl.iaad.engine;

import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Engine
{
    void setContext(Context context);

    Decision takeDecision();

    void acknowledgeResults(Result result);

    String deliverFinalReport();
}
