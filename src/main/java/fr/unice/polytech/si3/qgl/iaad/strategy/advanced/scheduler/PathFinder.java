package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial.ExploreTile;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial.MoveCrewTo;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial.ScheduleCrewPath;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.Comparator;
import java.util.Optional;

/**
 * @author Alexandre Clement
 * @since 22/03/2017.
 */
public class PathFinder
{
    private final Prioritisation prioritisation;
    private final Context context;
    private final SimulatedMap simulatedMap;
    private final IslandMap map;
    private final Crew crew;

    public PathFinder(Context context, IslandMap map, Crew crew)
    {
        this.context = context;
        simulatedMap = new SimulatedMap(map);
        this.map = map;
        this.crew = crew;
        prioritisation = new Prioritisation(context.getContract(), map, simulatedMap, new Area(simulatedMap), crew);
    }

    public Protocol next()
    {
        return prioritisation.nextLocationToExploit().map(this::move).orElse(new StopExploration());
    }

    private Protocol move(Point point)
    {
        Optional<Point> creekOptional = simulatedMap.getPoints().stream()
                .filter(creekLocation -> isUsefulToLandOn(point, creekLocation))
                .sorted(Comparator.comparingDouble(point::distance))
                .findFirst();

        if (creekOptional.isPresent())
            return new LandOnCreek(new ScheduleCrewPath(context, map, crew), map, creekOptional.get(), crew);

        if (point.equals(crew.getLocation()))
            return new ExploreTile(context, map, crew);
        return new MoveCrewTo(context, map, crew, point);
    }

    private boolean isUsefulToLandOn(Point destination, Point creekLocation)
    {
        boolean isACreek = !simulatedMap.getTile(creekLocation).getCreeks().isEmpty();
        double landCost = destination.distance(creekLocation) + crew.distanceToHomePort() + 10;
        double moveCost = destination.distance(crew.getLocation());
        boolean isCheaperToLandThanToMove = landCost <= moveCost;
        return isACreek && isCheaperToLandThanToMove;
    }
}
