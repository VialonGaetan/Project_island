package fr.unice.polytech.si3.qgl.iaad.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class EchoResultat
{
    private Result result;

    public EchoResultat(Result result)
    {
        this.result = result;
    }

    public int getCost()
    {
        return result.getCost();
    }

    public int getRange()
    {
        return result.getRange();
    }

    public Element getFound()
    {
        return result.getFound();
    }
}
