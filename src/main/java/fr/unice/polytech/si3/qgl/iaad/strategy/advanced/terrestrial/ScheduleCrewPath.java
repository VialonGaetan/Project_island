package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler.PathFinder;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ScheduleCrewPath implements Protocol
{
    private final Context context;
    private final IslandMap map;
    private final Crew crew;
    private Protocol protocol;

    public ScheduleCrewPath(Context context, IslandMap map, Crew crew)
    {
        this.context = context;
        this.map = map;
        this.crew = crew;
    }

    @Override
    public Decision takeDecision()
    {
        PathFinder pathFinder = new PathFinder(context, map, crew);
        protocol = pathFinder.next();
        return protocol.takeDecision();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return protocol.acknowledgeResults(result);
    }
}
