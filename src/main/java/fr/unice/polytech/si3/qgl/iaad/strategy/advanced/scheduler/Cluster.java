package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;

import java.awt.*;
import java.util.HashSet;

/**
 * @author Alexandre Clement
 * @since 22/03/2017.
 */
public class Cluster extends HashSet<Point>
{
    private final Biome biome;

    Cluster(Biome biome)
    {
        this.biome = biome;
    }

    public Biome getBiome()
    {
        return biome;
    }

    public Point getCenter()
    {
        int x = 0;
        int y = 0;
        for (Point point : this)
        {
            x += point.x;
            y += point.y;
        }
        return new Point(x / size(), y / size());
    }

    public int area()
    {
        return size();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Cluster cluster = (Cluster) o;

        return biome == cluster.biome;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (biome != null ? biome.hashCode() : 0);
        return result;
    }
}
