package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 * Accoste sur l'île
 */
public class Land implements Protocol
{
    /**
     * @return Stop
     */
    @Override
    public Action nextAction()
    {
        return new Stop();
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        return this;
    }
}
