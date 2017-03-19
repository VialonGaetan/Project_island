package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class Scheduler
{
    private final ModeledMap map;
    private final Point crewLocation;
    private final Map<Resource, Double> prioritisation;

    public Scheduler(ModeledMap map, Point crewLocation, Map<Resource, Double> prioritisation)
    {
        this.map = map;
        this.crewLocation = crewLocation;
        this.prioritisation = prioritisation;
    }

    public Queue<Resource> lifoResource()
    {
        return new PriorityQueue<>(prioritisation.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList()));
    }

    public List<Point> getClosestCluster(Resource resource)
    {
        if (resource.isPrimary())
            return getClosestCluster(PrimaryResource.valueOf(resource.toString()));
        return getClosestCluster(Manufactured.valueOf(resource.toString()));
    }

    private List<Point> getClosestCluster(PrimaryResource resource)
    {
        return map.biomeArea(resource).stream()
                .map(Rectangle::getLocation)
                .sorted(Comparator.comparingDouble(p1 -> p1.distance(crewLocation)))
                .collect(Collectors.toList());
    }

    private List<Point> getClosestCluster(Manufactured resource)
    {
        return resource.getRecipe().keySet().stream()
                .map(this::getClosestCluster)
                .map(Collection::stream)
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .collect(Collectors.toList());
    }
}
