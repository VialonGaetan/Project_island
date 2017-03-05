package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Protocol;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.*;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.*;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 03/03/2017.
 */
public class MoveOnMap implements Protocol{

    private Move_to action;
    private MoveToResultat result;
    private Compass direction;
    private int range;
    private Crew crew;
    private IslandMap map;
    private Map contrat;

    MoveOnMap(Compass direction,Map contrat, Crew crew, IslandMap map) {
        range=1;
        this.direction=direction;
        this.contrat = contrat;
        this.crew = crew;
        this.map = map;
        action = new Move_to(direction);
    }

    MoveOnMap(int range, Compass direction,  Map contrat, Crew crew, IslandMap map) {
        this.range=range;
        this.contrat = contrat;
        this.crew = crew;
        this.map = map;
        this.direction=direction;
        action = new Move_to(direction);
    }

    @Override
    public Decision takeDecision() {
        crew.move(direction);
        if (map.isOnBoard(crew.getLocation()))
            return action;
        return new Stop();
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        if(range == 1) {
            return new ExploreTuile(contrat,crew,map);
        }
        else{
            return new MoveOnMap(range--,direction,contrat,crew,map);
        }
    }
}
