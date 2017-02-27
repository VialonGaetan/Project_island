package fr.unice.polytech.si3.qgl.iaad.player.results;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;

import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class ExploreResultat {

    private Result result;

    public ExploreResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}

    public List<ResourceInformation> getResourcesExplored(){
        return result.getResourcesExplored();
    }
}
