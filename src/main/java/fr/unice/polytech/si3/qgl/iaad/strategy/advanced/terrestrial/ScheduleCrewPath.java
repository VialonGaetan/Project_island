package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.ground.GoToAPoint;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation.ModeledMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation.Prioritize;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler.Scheduler;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.StopExploration;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ScheduleCrewPath implements Protocol
{
    private final Context context;
    private final IslandMap map;
    private final Crew crew;
    private final Prioritize prioritize;
    private final Scheduler scheduler;
    private final Queue<Resource> lifo;
    private Protocol protocol;

    public ScheduleCrewPath(Context context, IslandMap map, Crew crew)
    {
        this.context = context;
        this.map = map;
        this.crew = crew;
        ModeledMap modeledmap = new ModeledMap(map);
        prioritize = new Prioritize(modeledmap, context.getContract());
        scheduler = new Scheduler(modeledmap, crew.getLocation(), prioritize.getPrioritizedContract());
        lifo = scheduler.lifoResource();
    }

    @Override
    public Decision takeDecision()
    {
        if (lifo.isEmpty())
            protocol = new StopExploration();
        else
        {
            List<Point> closestCluster = scheduler.getClosestCluster(lifo.peek()).stream()
                    .filter(point -> !map.getTile(point).isAlready(GroundActionTile.VISITED))
                    .collect(Collectors.toList());

            if (closestCluster.isEmpty())
            {
                lifo.poll();
                return takeDecision();
            }
            else if (closestCluster.contains(crew.getLocation()))
                protocol = new ExploreTile(context, map, crew, prioritize.getPrioritizedContract());
            else
                protocol = new GoToAPoint(context, map, crew, closestCluster.get(0));
        }
        return protocol.takeDecision();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return protocol.acknowledgeResults(result);
    }
}
