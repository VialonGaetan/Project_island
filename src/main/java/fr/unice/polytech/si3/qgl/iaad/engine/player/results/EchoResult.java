package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.map.Element;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class EchoResult
{
    private Result result;

    public EchoResult(Result result)
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
