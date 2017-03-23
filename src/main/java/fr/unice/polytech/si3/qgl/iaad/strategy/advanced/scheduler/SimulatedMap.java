package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.Tile;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public class SimulatedMap extends IslandMap
{
    public SimulatedMap(IslandMap islandMap)
    {
        super(islandMap);
    }

    @Override
    public Tile getTile(Point point)
    {
        Tile tile = super.getTile(point);
        if (alreadyChecked(tile))
            return tile;

        Tile simulated = new Tile();

        for (int x = point.x - 1; x <= point.x + 1; x++)
        {
            for (int y = point.y - 1; y <= point.y + 1; y++)
            {
                if (getPoints().contains(new Point(x, y)))
                {
                    simulated.addBiomes(super.getTile(new Point(x, y)).getBiomes());
                    simulated.addResourceInformationList(super.getTile(new Point(x, y)).getResourceInformationList());
                }
            }
        }
        return simulated;
    }

    private boolean alreadyChecked(Tile tile)
    {
        for (GroundActionTile groundActionTile : GroundActionTile.values())
        {
            if (tile.isAlready(groundActionTile))
                return true;
        }
        return false;
    }
}

