package fr.unice.polytech.si3.qgl.iaad.util.map;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author romain
 * Created on 12/02/17.
 */
public class IslandMap
{
    private Map<Point, Tile> map;
    private Map<Compass, Integer> dimensions;

    /**
     * IslandMap constructor
     * IslandMap contains a new Tile
     */
    public IslandMap()
    {
        map = new HashMap<>();
        dimensions = new HashMap<>();

        map.put(new Point(), new Tile());

        for(Compass direction : Compass.values())
        {
            dimensions.put(direction, 0);
        }
    }

    /**
     * Grow the IslandMap in a get of a number of points
     * @param direction where the IslandMap has to grow
     * @param range, the number of points
     */
    public void grow(Compass direction, int range) { dimensions.put(direction, dimensions.get(direction)+range); }

    /**
     * EchoCheck if the such point is located on the IslandMap
     * @param point, the point to analyze
     * @return true if the point is on the IslandMap, false otherwise
     */
    public boolean isOnBoard(Point point)
    {
        boolean test = false;

        if((point.x >= -dimensions.get(Compass.W)) && (point.x <= dimensions.get(Compass.E)))
        {
            if((point.y <= dimensions.get(Compass.S)) && (point.y >= -dimensions.get(Compass.N)))
            {
                test = true;
            }
        }

        return test;
    }

    /**
     * Get the such tile from the IslandMap at this point
     * @param point, the point where is located the tile
     * @return null if the point is not on the IslandMap
     */
    public Tile getTile(Point point)
    {
        Tile tile = null;

        if(isOnBoard(point))
        {
            if(!map.containsKey(point))
            {
                map.put(point, new Tile());
            }

            tile = map.get(point);
        }

        return tile;
    }

    /**
     * Get a fr.unice.polytech.si3.qgl.iaad.ground tile ie the aerial tile duplicated 8 times
     * @param point, the point of the aerial map
     * @param tile, the tile of the aerial map
     * @return a Map that contains the 9 tiles
     */
    private Map<Point, Tile> getGroundTiles(Point point, Tile tile)
    {
        Map<Point, Tile> tiles = new HashMap<>();

        point.x *= 3;
        point.y *= 3;

        tiles.put(point, tile);

        tiles.put(new Point(point.x-1, point.y+1), new Tile(tile));
        tiles.put(new Point(point.x, point.y+1), new Tile(tile));
        tiles.put(new Point(point.x+1, point.y+1), new Tile(tile));
        tiles.put(new Point(point.x-1, point.y), new Tile(tile));
        tiles.put(new Point(point.x+1, point.y), new Tile(tile));
        tiles.put(new Point(point.x-1, point.y-1), new Tile(tile));
        tiles.put(new Point(point.x, point.y-1), new Tile(tile));
        tiles.put(new Point(point.x+1, point.y-1), new Tile(tile));

        return tiles;
    }

    /**
     * convert the aerial map in ground map
     */
    public void zoom()
    {
        Map<Point, Tile> groundMap = new HashMap<>();

        Set<Point> keySet = map.keySet();
        Iterator<Point> iterator = keySet.iterator();

        while (iterator.hasNext())
        {
            Point point = new Point(iterator.next());
            groundMap.putAll(getGroundTiles(point, map.get(point)));
        }

        for(Compass direction : Compass.values())
        {
            dimensions.put(direction, dimensions.get(direction)*3+1);
        }

        map = groundMap;
    }

    /**
     * Get the points of the current Map
     * @return Set<Point>
     */
    public Set<Point> getPoints() { return map.keySet(); }

    /**
     * Get the dimension in a direction
     * @param direction that we want to know the dimension
     * @return int
     */
    public int getDimension(Compass direction)
    {
        return dimensions.get(direction);
    }
}
