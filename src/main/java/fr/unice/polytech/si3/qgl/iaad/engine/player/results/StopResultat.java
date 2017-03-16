package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class StopResultat {

    private Result result;

    public StopResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}
}
