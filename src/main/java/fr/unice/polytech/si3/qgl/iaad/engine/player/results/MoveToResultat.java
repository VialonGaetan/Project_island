package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class MoveToResultat {

    private Result result;

    public MoveToResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}
}
