package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author romain
 * Created on 12/02/17.
 */
public class Board implements fr.unice.polytech.si3.qgl.iaad.future.Board
{
    private Map<Point, Tile> map;
    private Map<Direction, Point> dimensions;

    /**
     * Board constructor
     * Board contains a new Tile
     */
    public Board()
    {
        map = new HashMap<>();
        dimensions = new HashMap<>();

        map.put(new Point(), new Tile());
        dimensions.put(Direction.E, new Point());
        dimensions.put(Direction.S, new Point());
        dimensions.put(Direction.W, new Point());
        dimensions.put(Direction.N, new Point());
    }

    /**
     * Grow the Board in a direction of a number of points
     * @param direction where the Board has to grow
     * @param range, the number of points
     */
    @Override
    public void grow(Direction direction, int range)
    {
        Point limit = dimensions.get(direction);

        switch(direction)
        {
            case N:
                limit.y += range;
                break;
            case E:
                limit.x += range;
                break;
            case S:
                limit.y -= range;
                break;
            case W:
                limit.x -= range;
                break;
        }
    }

    /**
     * Check if the such point is located on the Board
     * @param point, the point to analyze
     * @return true if the point is on the Board, false otherwise
     */
    @Override
    public boolean isOnBoard(Point point)
    {
        boolean test = false;

        if((point.x >= dimensions.get(Direction.W).x) && (point.x <= dimensions.get(Direction.E).x))
        {
            if((point.y >= dimensions.get(Direction.S).y) && (point.y <= dimensions.get(Direction.N).y))
            {
                test = true;
            }
        }

        return test;
    }

    /**
     * Get the such tile from the Board at this point
     * @param point, the point where is located the tile
     * @return null if the point is not on the Board
     */
    @Override
    public fr.unice.polytech.si3.qgl.iaad.islandMap.Tile getTile(Point point)
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
     * Get a ground tile ie the aerial tile duplicated 8 times
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
        Map<Point, Tile> groundMap = new HashMap();

        Set<Point> keySet = map.keySet();
        Iterator<Point> iterator = keySet.iterator();

        while (iterator.hasNext())
        {
            Point point = new Point(iterator.next());
            groundMap.putAll(getGroundTiles(point, map.get(point)));
        }

        map = groundMap;
    }

    /**
     * Get the current Map
     * @return a copy of the true Map
     */
    public Map<Point, Tile> getMap() { return new HashMap<>(map); }
}
