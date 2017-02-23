package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.format.json.JsonResult;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class StopResultat {

    JsonResult result;

    StopResultat(JsonResult result) {
        this.result = result;
    }

    public int getCots(){return result.getCost();}
}
