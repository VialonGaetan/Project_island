package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.resource.GlimpseInformation;

import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class GlimpseResult {

    Result result;

    public GlimpseResult(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}

    public List<GlimpseInformation> getGlimpseInformation(){
        return result.getGlimpseInformation();
    }
}
