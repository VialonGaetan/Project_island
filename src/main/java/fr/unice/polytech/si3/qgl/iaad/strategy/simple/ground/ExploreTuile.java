package fr.unice.polytech.si3.qgl.iaad.strategy.simple.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ExploreResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 03/03/2017.
 */
public class ExploreTuile implements Protocol {

    private Explore action;
    private ExploreResult exploreResult;
    private IslandMap map;
    private Crew crew;
    private Map contrat;
    private boolean containFish;
    private Compass direction;
    private Compass sense;


    public ExploreTuile(Compass direction,Compass sense, Map contrat, Crew crew, IslandMap map) {
        this.contrat=contrat;
        this.direction=direction;
        this.crew=crew;
        this.map=map;
        this.sense=sense;
        action = new Explore();
        containFish = false;

    }

    @Override
    public Decision takeDecision() {
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        exploreResult = new ExploreResult(result);
        map.getTile(crew.getLocation()).addResourceInformationList(exploreResult.getResourcesExplored());
        for (int i = 0; i < exploreResult.getResourcesExplored().size(); i++) {
            Resource resource = exploreResult.getResourcesExplored().get(i).getResource();
            if(resource.equals(Resource.FISH))
                containFish=true;
            if (contrat.containsKey(resource) && !map.getTile(crew.getLocation()).resourceAlreadyExploited(resource))
            {
                return new ExploitResource(resource,direction,sense,contrat,crew,map);
            }
        }
        if(containFish){
            return new SeeOcean(direction,sense,contrat,crew,map);
        }
        return new MoveOnMap(direction,sense,contrat,crew,map);
    }


}
