package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.format.Result;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class LandResultat {

    private Result result;

    public LandResultat(Result result) {
        this.result = result;
    }

    public int getCots(){return result.getCost();}
}
