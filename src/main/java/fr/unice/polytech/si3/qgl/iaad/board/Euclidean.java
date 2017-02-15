package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandre Clement
 * @since 15/02/2017.
 */
public class Euclidean
{
    private Euclidean()
    {

    }

    public static Optional<Creek> getClosestCreekToTheEmergencySite(Board board)
    {
        List<Point> creekLocation = new ArrayList<>();
        Point emergencySite = browseBoard(board, creekLocation);

        if (emergencySite == null)
            return Optional.empty();

        Comparator<Point> sortByDistanceToSite = Comparator.comparingDouble(p -> p.distance(emergencySite));
        Optional<Point> closestCreek = creekLocation.stream().sorted(sortByDistanceToSite).findFirst();

        if (closestCreek.isPresent())
            return Optional.of(board.getTile(closestCreek.get()).getCreeks().get(0));
        return Optional.empty();
    }

    private static Point browseBoard(Board board, List<Point> creekLocation)
    {
        Point emergencySite = browseAnArea(board, creekLocation, Direction.SE).orElse(null);
        emergencySite = browseAnArea(board, creekLocation, Direction.NE).orElse(emergencySite);
        emergencySite = browseAnArea(board, creekLocation, Direction.SW).orElse(emergencySite);
        emergencySite = browseAnArea(board, creekLocation, Direction.NW).orElse(emergencySite);
        return emergencySite;
    }

    private static Optional<Point> browseAnArea(Board board, List<Point> creekLocation, Direction direction)
    {
        Optional<Point> emergencySiteLocationOptional = Optional.empty();
        Point location = new Point();
        while (board.isOnBoard(location))
        {
            while (board.isOnBoard(location))
            {
                if (!board.getTile(location).getCreeks().isEmpty())
                    creekLocation.add(new Point(location));
                if (!board.getTile(location).getEmergencySites().isEmpty())
                    emergencySiteLocationOptional = Optional.of(location);
                location.translate(direction.getVecteur().x, 0);
            }
            location.setLocation(0, location.y + direction.getVecteur().y);
        }
        return emergencySiteLocationOptional;
    }
}
