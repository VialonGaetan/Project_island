package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 22/03/2017.
 */
public class Area
{
    private final Map<Biome, Cluster> areaMap;

    public Area(IslandMap map)
    {
        areaMap = new EnumMap<>(Biome.class);

        for (Biome biome : Biome.values())
        {
            areaMap.put(biome, new Cluster(biome));
        }

        for (Point point : map.getPoints())
        {
            for (Biome biome : map.getTile(point).getBiomes())
            {
                areaMap.get(biome).add(point);
            }
        }
    }

    public Cluster getCluster(Biome biome)
    {
        return areaMap.get(biome);
    }

    public int getArea(Biome biome)
    {
        return areaMap.get(biome).area();
    }
}
