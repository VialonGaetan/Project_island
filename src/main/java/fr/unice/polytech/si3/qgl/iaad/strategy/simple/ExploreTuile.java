package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ExploreResultat;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 03/03/2017.
 */
public class ExploreTuile implements Protocol {

    private Explore action;
    private ExploreResultat exploreResult;
    private IslandMap map;
    private Crew crew;
    private Map contrat;
    private boolean containFish;


    public ExploreTuile(Map contrat, Crew crew, IslandMap map) {
        this.contrat=contrat;
        this.crew=crew;
        this.map=map;
        action = new Explore();
        containFish = false;
    }

    @Override
    public Decision takeDecision() {
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        exploreResult = new ExploreResultat(result);
        map.getTile(crew.getLocation()).addResourceInformations(exploreResult.getResourcesExplored());
        for (int i = 0; i < exploreResult.getResourcesExplored().size(); i++) {
            Resource resource = exploreResult.getResourcesExplored().get(i).getResource();
            if(resource.equals(Resource.FISH) && i == 0)
                containFish=true;
            if (contrat.containsKey(resource) && !map.getTile(crew.getLocation()).resourceAlreadyExploited(resource))
            {
                return new ExploitResource(resource,contrat,crew,map);
            }
        }
        if(containFish){
            return new UTurn(choseCompass(),choseSensUTurn(),contrat,crew,map);
        }
        return new MoveOnMap(choseCompass(),contrat,crew,map);
    }

    private Compass choseCompass(){
        Point position = crew.getLocation();
        position.y = (int) position.getY() - 1;
        if (! map.getTile(position).isAlreadyVisited() && map.isOnBoard(position))
            return Compass.N;
        else
            return Compass.S;
    }

    private Compass choseSensUTurn(){
        Point position = crew.getLocation();
        position.y = (int) position.getX() + 1;
        if (! map.getTile(position).isAlreadyVisited() && map.isOnBoard(position))
            return Compass.E;
        else
            return Compass.W;
    }
}
