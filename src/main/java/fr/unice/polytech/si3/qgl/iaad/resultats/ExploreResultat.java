package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.resource.ResourceInformation;

import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class ExploreResultat {

    JsonResult result;

    ExploreResultat(JsonResult result) {
        this.result = result;
    }

    public int getCots(){return result.getCost();}

    public List<ResourceInformation> getResourcesExplored(){
        return result.getResourcesExplored();
    }
}
