package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Move_to;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler.SimulatedMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        List<Map.Entry<Compass, Double>> difficultyList = new ArrayList<>();
        SimulatedMap simulatedMap = new SimulatedMap(map);
        for (Compass compass : Compass.values())
        {
            simulation = new Crew(crew);
            simulation.move(compass);
            if (simulation.getLocation().distance(target) < currentDistance && map.isOnMap(simulation.getLocation()))
            {
                difficultyList.add(new AbstractMap.SimpleEntry<>(compass, simulatedMap.getTile(simulation.getLocation()).getBiomes().stream().mapToDouble(Biome::getCrossFactor).average().orElse(Biome.OCEAN.getCrossFactor())));
            }
        }
        Optional<Compass> compass = difficultyList.stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).findFirst();
        if (!compass.isPresent())
            return new Stop();
        crew.move(compass.get());
        return new Move_to(compass.get());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        if (crew.getLocation().equals(target))
            return new ExploreTile(context, map, crew);
        return this;
    }
}
