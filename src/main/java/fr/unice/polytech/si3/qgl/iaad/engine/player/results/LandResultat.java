package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class LandResultat {

    private Result result;

    public LandResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}
}
