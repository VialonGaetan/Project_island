package fr.unice.polytech.si3.qgl.iaad.player.results;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class TransformResultat {

    Result result;

    TransformResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}

    public int getTransformProduction(){
        return result.getTransformProduction();
    }
}
