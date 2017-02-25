package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.format.Result;

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

    public int getCots()
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
