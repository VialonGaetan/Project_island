package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Direction;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 15/02/2017.
 */
public class StandardBoard implements Board
{
    private final Map<Point, Tile> map;
    private final Map<Direction, Integer> dimensions;

    public StandardBoard()
    {
        map = new HashMap<>();
        map.put(new Point(), new StandardTile());
        dimensions = new EnumMap<>(Direction.class);
        for (Direction direction : Direction.values())
            dimensions.put(direction, 0);
    }

    @Override
    public void grow(Direction direction, int range)
    {
        for (int i = 0; i < range; i++)
            grow(direction);
    }

    private void grow(Direction direction)
    {
        Point vector = direction.getVecteur();
        Point extremum = new Point(vector.x * dimensions.get(direction), vector.y * dimensions.get(direction));
        browsePerpendicular(extremum, vector, true);
        browsePerpendicular(extremum, vector, false);
        dimensions.put(direction, dimensions.get(direction) + 1);
    }

    private void browsePerpendicular(Point extremum, Point vector, boolean right)
    {
        Point location = new Point(extremum);

        int dx = right ? vector.x : -vector.x;
        int dy = right ? vector.y : -vector.y;

        while (isOnBoard(location))
        {
            map.put(new Point(location.x + vector.x, location.y + vector.y), new StandardTile());
            location.translate(dy, dx);
        }
    }

    @Override
    public boolean isOnBoard(Point point)
    {
        boolean north = dimensions.get(Direction.N) >= -point.y;
        boolean east = dimensions.get(Direction.E) >= point.x;
        boolean west = dimensions.get(Direction.W) >= -point.x;
        boolean south = dimensions.get(Direction.S) >= point.y;
        return north && east && west && south;
    }

    @Override
    public Tile getTile(Point point)
    {
        return map.get(point);
    }

    @Override
    public String toString()
    {
        return map.toString();
    }
}
