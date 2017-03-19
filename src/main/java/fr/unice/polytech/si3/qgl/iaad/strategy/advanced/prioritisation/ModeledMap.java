package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.prioritisation;

import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ModeledMap
{
    private final IslandMap map;
    private final Map<Biome, Set<Rectangle>> biomeAreaMap;

    public ModeledMap(IslandMap map)
    {
        this.map = map;
        biomeAreaMap = new EnumMap<>(Biome.class);
        for (Biome biome : Biome.values())
        {
            biomeAreaMap.put(biome, biomeArea(biome));
        }
    }

    private Set<Rectangle> biomeArea(Biome biome)
    {
        Set<Rectangle> rectangles = new HashSet<>();

        for (Point point : map.getPoints())
        {
            if (map.getTile(point).getBiomes().contains(fr.unice.polytech.si3.qgl.iaad.util.resource.Biome.valueOf(biome.toString())))
                    rectangles.add(new Rectangle(point.x, point.y, 1, 1));
        }

        return rectangles;
    }

    public Set<Rectangle> biomeArea(PrimaryResource primaryResource)
    {
        Set<Rectangle> area = new HashSet<>();
        for (Biome biome : Biome.values())
        {
            if (biome.getResourceMap().contains(primaryResource))
                area.addAll(biomeArea(biome));
        }
        return area;
    }

    private double getComputedArea(Biome biome)
    {
        return biomeAreaMap.get(biome).stream()
                .map(rectangle -> rectangle.getHeight() * rectangle.getWidth())
                .reduce(Double::sum).orElse(0d);
    }

    double getComputedArea(PrimaryResource resource)
    {
        double area = 0;

        for (Biome biome : Biome.values())
        {
            if (biome.getResourceMap().contains(resource))
                area += getComputedArea(biome) * biome.getResourceMap().get(resource) / biome.getCrossFactor();
        }

        return area;
    }

    @Override
    public String toString()
    {
        return biomeAreaMap.toString();
    }
}
