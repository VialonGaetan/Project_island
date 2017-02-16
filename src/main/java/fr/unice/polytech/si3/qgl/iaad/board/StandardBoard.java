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
        growSide(extremum, direction, true);
        growSide(extremum, direction, false);
        dimensions.put(direction, dimensions.get(direction) + 1);
    }

    private void growSide(Point extremum, Direction direction, boolean right)
    {
        Point vector = direction.getVecteur();
        Point location = new Point(extremum);
        Point side = right ? direction.getRight().getVecteur() : direction.getLeft().getVecteur();

        while (isOnBoard(location))
        {
            map.put(new Point(location.x + vector.x, location.y + vector.y), new StandardTile());
            location.translate(side.x, side.y);
        }
    }

    @Override
    public boolean isOnBoard(Point point)
    {
        return map.containsKey(point);
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
