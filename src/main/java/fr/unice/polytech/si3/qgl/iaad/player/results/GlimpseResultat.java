package fr.unice.polytech.si3.qgl.iaad.player.results;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;

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
