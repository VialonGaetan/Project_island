package fr.unice.polytech.si3.qgl.iaad.strategy.simple.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Glimpse;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.GlimpseResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.GlimpseInformation;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.List;
import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 11/03/2017.
 */
public class SeeOcean implements Protocol{

    private Glimpse action;
    private GlimpseResult glimpseResult;
    private Compass direction;
    private int range;
    private Crew crew;
    private IslandMap map;
    private Map contrat;
    private Compass sense;

    public SeeOcean(int range,Compass direction,Compass sense, Map contrat,Crew crew, IslandMap map) {
        this.direction = direction;
        this.range = range;
        this.crew = crew;
        this.map = map;
        this.sense=sense;
        this.contrat = contrat;
        action = new Glimpse(direction,range);
    }

    public SeeOcean(Compass direction, Compass sense,Map contrat, Crew crew, IslandMap map) {
        this.direction = direction;
        this.range = 4;
        this.crew = crew;
        this.map = map;
        this.contrat = contrat;
        this.sense=sense;
        action = new Glimpse(direction,range);
    }

    @Override
    public Decision takeDecision() {
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        glimpseResult = new GlimpseResult(result);
        if (viewOcean(glimpseResult.getGlimpseInformation()))
            return new UTurn(direction.get(Direction.BACK),sense,contrat,crew,map);

        else
            return new MoveOnMap(direction,sense,contrat,crew,map);
    }

    private boolean viewOcean(List<GlimpseInformation> glimpseInformations){
        for (GlimpseInformation biomes: glimpseInformations) {
            if(!biomes.getBiome().equals(Biome.OCEAN))
                return false;
        }
        return true;
    }
}
