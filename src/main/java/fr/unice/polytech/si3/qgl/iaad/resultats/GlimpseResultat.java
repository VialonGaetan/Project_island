package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.actions.Glimpse;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.format.json.JsonResult;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class GlimpseResultat {

    Result result;

    GlimpseResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}
}
