package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

import java.awt.*;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 *         Accoste sur l'île
 */
public class LandOnIsland implements Protocol
{
    private IslandMap map;
    private Point position;

    LandOnIsland(IslandMap map, Point position)
    {
        this.map = map;
        this.position = position;
    }

    /**
     * @return Land
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        return new Land(map.getCreekIds(position)[0], 1);
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        // Passage en phase terrestre
        return new StopAerial();
    }
}
