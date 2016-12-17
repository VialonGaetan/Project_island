package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 16/12/2016.
 */
public class StopAerial implements Protocol
{
    @Override
    public Action nextAction() throws InvalidMapException
    {
        return new Stop();
    }

    @Override
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        return null;
    }
}
