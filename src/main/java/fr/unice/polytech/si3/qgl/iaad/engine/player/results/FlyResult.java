package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class FlyResult {
    private Result result;

    public FlyResult(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}
}
