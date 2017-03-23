package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
public class LandOnCreek implements Protocol
{

    private final Protocol exit;
    private final IslandMap map;
    private final Point creekLocation;
    private final Crew crew;

    public LandOnCreek(Protocol exit, IslandMap map, Point creekLocation, Crew crew)
    {
        this.exit = exit;
        this.map = map;
        this.creekLocation = creekLocation;
        this.crew = crew;
    }

    @Override
    public Decision takeDecision()
    {
        return new Land(map.getTile(creekLocation).getCreeks().get(0).getId(), crew.getPeople());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        crew.land(creekLocation);
        return exit;
    }
}
