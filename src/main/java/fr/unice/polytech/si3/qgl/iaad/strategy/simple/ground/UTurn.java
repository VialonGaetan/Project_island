package fr.unice.polytech.si3.qgl.iaad.strategy.simple.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.MoveToResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 03/03/2017.
 */
public class UTurn implements Protocol {

    private Move_to action;
    private MoveToResult moveToResult;
    private Compass direction;
    private Crew crew;
    private IslandMap map;
    private Map contrat;
    private Compass sense;

    UTurn(Compass direction,Map contrat, Crew crew, IslandMap map) {
        this.direction=direction;
        this.contrat=contrat;
        this.crew=crew;
        this.map=map;
        this.sense=choseSensUTurn();
        action = new Move_to(sense);
    }

    UTurn(Compass direction,Compass sense,Map contrat, Crew crew, IslandMap map) {
        this.direction=direction;
        this.contrat=contrat;
        this.crew=crew;
        this.map=map;
        this.sense=sense;
        action = new Move_to(sense);
    }

    @Override
    public Decision takeDecision() {
        crew.move(sense);
        if (map.isOnMap(crew.getLocation()))
            return action;
        return new Stop();
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        if(direction.equals(sense))
            return new MoveOnMap(direction, contrat, crew, map);
        else
            return new UTurn(direction,direction,contrat,crew,map);
    }

    private Compass choseSensUTurn(){
        Point position = crew.getLocation();
        position.y = (int) position.getX() + 1;
        if (map.isOnMap(position) && !map.getTile(position).isAlready(GroundActionTile.VISITED) && !map.getTile(position).getBiomes().contains(Biome.OCEAN))
            return Compass.E;
        else
            return Compass.W;
    }
}
