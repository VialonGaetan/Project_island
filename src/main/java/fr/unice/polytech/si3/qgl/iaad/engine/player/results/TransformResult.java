package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class TransformResult {

    private Result result;

    public TransformResult(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}

    public int getTransformProduction(){
        return result.getTransformProduction();
    }
}
