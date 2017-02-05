package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * On Fly au dessus de l'île.
 *
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
class FlyOnIsland extends Oriented implements Protocol
{
    FlyOnIsland(IslandMap map, Direction heading, Direction sense)
    {
        super(map, heading, sense);
    }

    @Override
    public Action nextAction() throws InvalidMapException
    {
        getMap().moveLocation(getHeading());
        return new Fly();
    }

    @Override
    public Protocol setResult(Area result)
    {
        return new ScanIsland(getMap(), getHeading(), getSense());
    }
}
