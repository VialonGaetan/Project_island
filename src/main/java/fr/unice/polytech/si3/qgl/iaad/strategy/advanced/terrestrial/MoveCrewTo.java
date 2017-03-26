package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.Tile;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public class MoveCrewTo implements Protocol
{
    private final Context context;
    private final IslandMap map;
    private final Crew crew;
    private final Point target;

    public MoveCrewTo(Context context, IslandMap map, Crew crew, Point target)
    {
        this.context = context;
        this.map = map;
        this.crew = crew;
        this.target = target;
    }

    @Override
    public Decision takeDecision()
    {
        Crew simulation;
        double currentDistance = crew.getLocation().distance(target);
        Map<Compass, Double> orderedDifficulty = new EnumMap<>(Compass.class);
        for (Compass compass : Compass.values())
        {
            simulation = new Crew(crew);
            simulation.move(compass);
            if (simulation.getLocation().distance(target) < currentDistance && map.isOnMap(simulation.getLocation()))
            {
                orderedDifficulty.put(compass, getTileDifficulty(map.getTile(simulation.getLocation())));
            }
        }
        Optional<Compass> compass = orderedDifficulty.entrySet().stream().sorted(Comparator.comparingDouble(Map.Entry::getValue)).findFirst().map(Map.Entry::getKey);
        if (!compass.isPresent())
            return new Stop();
        crew.move(compass.get());
        return new Move_to(compass.get());
    }

    private double getTileDifficulty(Tile tile)
    {
        return tile.getBiomes().stream().mapToDouble(Biome::getCrossFactor).average().orElse(Biome.OCEAN.getCrossFactor());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        if (crew.getLocation().equals(target))
            return new ExploreTile(context, map, crew);
        return this;
    }
}
