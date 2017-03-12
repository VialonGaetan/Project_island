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

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 12/03/2017.
 */
public class Initialize implements Protocol {

    private Glimpse action;
    private GlimpseResult glimpseResult;
    private Compass direction;
    private Compass sense;
    private int range;
    private Crew crew;
    private IslandMap map;
    private Map contrat;

    public Initialize(Map contrat, Crew crew, IslandMap map) {
        this.crew = crew;
        this.map = map;
        this.contrat = contrat;
        direction=Compass.N;
        sense=Compass.E;
        action = new Glimpse(sense,2);
    }

    @Override
    public Decision takeDecision() {
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        glimpseResult = new GlimpseResult(result);
        for (GlimpseInformation biomes: glimpseResult.getGlimpseInformation()) {
            if(biomes.getBiome().equals(Biome.OCEAN) && biomes.getPercentage()>50)
                return new ExploreTuile(direction,sense.get(Direction.BACK),contrat,crew,map);
        }
        return new ExploreTuile(direction,sense,contrat,crew,map);

    }
}
