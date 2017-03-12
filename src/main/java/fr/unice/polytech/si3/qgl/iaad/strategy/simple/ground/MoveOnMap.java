package fr.unice.polytech.si3.qgl.iaad.strategy.simple.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.MoveToResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 03/03/2017.
 */
public class MoveOnMap implements Protocol{

    private Move_to action;
    private MoveToResult result;
    private Compass direction;
    private int range;
    private Crew crew;
    private IslandMap map;
    private Map contrat;
    private Compass sense;

    MoveOnMap(Compass direction,Compass sense,Map contrat, Crew crew, IslandMap map) {
        range=1;
        this.direction=direction;
        this.contrat = contrat;
        this.crew = crew;
        this.map = map;
        this.sense=sense;
        action = new Move_to(direction);
    }

    MoveOnMap(int range, Compass direction,Compass sense,  Map contrat, Crew crew, IslandMap map) {
        this.range=range;
        this.contrat = contrat;
        this.crew = crew;
        this.map = map;
        this.sense=sense;
        this.direction=direction;
        action = new Move_to(direction);
    }

    @Override
    public Decision takeDecision() {
        crew.move(direction);
        if (map.isOnMap(crew.getLocation()))
            return action;
        return new Stop();
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        if(range == 1) {
            return new ExploreTuile(direction,sense,contrat,crew,map);
        }
        else{
            return new MoveOnMap(range--,direction,sense,contrat,crew,map);
        }
    }
}
