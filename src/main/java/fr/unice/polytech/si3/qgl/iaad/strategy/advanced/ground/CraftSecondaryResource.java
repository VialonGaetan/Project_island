package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Glimpse;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Transform;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.GlimpseResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.TransformResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 13/03/2017.
 */
public class CraftSecondaryResource implements Protocol {

    private Transform action;
    private TransformResult transformResult;
    private Resource resource;
    private Crew crew;
    private IslandMap map;
    private Map<Resource,Integer> contrat;
    private Protocol exit;

    public CraftSecondaryResource(Resource secondaryResource, Protocol exit, Map contrat, Crew crew, IslandMap map) {
    this.contrat=contrat;
    this.crew=crew;
    this.map=map;
    this.resource=secondaryResource;
    this.exit=exit;
    if (secondaryResource.getReagent2().equals(null)){
        this.contrat.put(resource.getReagent1(),this.contrat.get(resource.getReagent1())-resource.getQte1());
        action = new Transform(secondaryResource.getReagent1(),secondaryResource.getQte1());
    }
    else
        action = new Transform(secondaryResource.getReagent1(),secondaryResource.getQte1(),secondaryResource.getReagent2(),secondaryResource.getQte2());
        this.contrat.put(resource.getReagent2(),this.contrat.get(resource.getReagent2())-resource.getQte2());
        this.contrat.put(resource.getReagent1(),this.contrat.get(resource.getReagent1())-resource.getQte1());
    }


    @Override
    public Decision takeDecision() {
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        transformResult = new TransformResult(result);
        contrat.put(resource,contrat.get(resource)-transformResult.getTransformProduction());
        return exit;
    }
}
