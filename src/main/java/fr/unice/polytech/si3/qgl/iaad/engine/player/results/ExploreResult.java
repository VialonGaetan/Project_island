package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;

import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class ExploreResult {

    private Result result;

    public ExploreResult(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}

    public List<ResourceInformation> getResourcesExplored(){
        return result.getResourcesExplored();
    }
}
