package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import java.awt.Point;
import java.util.*;

/**
 * @author romain
 * Created on 12/02/17.
 */
public class Board
{
    private Map<Point, Tile> map;
    private Map<Direction, Integer> dimensions;

    /**
     * Board constructor
     * Board contains a new Tile
     */
    public Board()
    {
        map = new HashMap<>();
        dimensions = new HashMap<>();

        map.put(new Point(), new Tile());

        for(Direction direction : Direction.values())
        {
            dimensions.put(direction, 0);
        }
    }

    /**
     * Grow the Board in a direction of a number of points
     * @param direction where the Board has to grow
     * @param range, the number of points
     */
    public void grow(Direction direction, int range) { dimensions.put(direction, dimensions.get(direction)+range); }

    /**
     * Check if the such point is located on the Board
     * @param point, the point to analyze
     * @return true if the point is on the Board, false otherwise
     */
    public boolean isOnBoard(Point point)
    {
        boolean test = false;

        if((point.x >= -dimensions.get(Direction.W)) && (point.x <= dimensions.get(Direction.E)))
        {
            if((point.y <= dimensions.get(Direction.S)) && (point.y >= -dimensions.get(Direction.N)))
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
        Map<Point, Tile> groundMap = new HashMap();

        Set<Point> keySet = map.keySet();
        Iterator<Point> iterator = keySet.iterator();

        while (iterator.hasNext())
        {
            Point point = new Point(iterator.next());
            groundMap.putAll(getGroundTiles(point, map.get(point)));
        }

        for(Direction direction : Direction.values())
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

    public int getDimension(Direction direction)
    {
        return dimensions.get(direction);
    }
}
