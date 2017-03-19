package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.ground;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 18/03/2017.
 */
public class GoToAPoint implements Protocol {

    private Point dest;
    private Map contrat;
    private Crew crew;
    private IslandMap map;
    private Move_to action;
    private Compass direction;


    public GoToAPoint(Point dest, Map contrat, Crew crew, IslandMap map) {
        this.dest = dest;
        this.contrat = contrat;
        this.crew = crew;
        this.map = map;
        int distanceX = dest.x - crew.getLocation().x;
        int distanceY = dest.y - crew.getLocation().y;
        if (distanceY != 0) {
            action = (distanceY > 0) ? new Move_to(direction = Compass.S) : new Move_to(direction = Compass.N);
        }
        else
            action = (distanceX > 0) ? new Move_to(direction =Compass.E) : new Move_to(direction = Compass.W);

    }

    @Override
    public Decision takeDecision() {
        crew.move(direction);
        if (!map.isOnMap(crew.getLocation()))
            return new Stop();
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        if (dest.equals(crew.getLocation()))
            return new StopExploration();
        else
            return new GoToAPoint(dest,contrat,crew,map);
    }
}
