package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 16/12/2016.
 *         <p>
 *         Arrete la partie lors de la phase aérienne
 */
class StopAerial implements Protocol
{
    /**
     * @return Stop
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        return new Stop();
    }

    /**
     * @param result le résultat de l'action effectué
     * @return null
     */
    @Override
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        return null;
    }
}
