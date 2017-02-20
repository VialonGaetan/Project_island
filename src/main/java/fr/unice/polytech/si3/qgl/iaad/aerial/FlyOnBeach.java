package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * On Fly au dessus des côtes.
 *
 * @author Alexandre Clement
 * @since 30/01/2017.
 */
class FlyOnBeach extends Oriented implements Protocol
{

    FlyOnBeach(IslandMap map, Direction heading, Direction sense)
    {
        super(map, heading, sense);
    }

    /**
     * @return Fly
     * @throws InvalidMapException si on sort de la carte
     */
    @Override
    public Decision nextAction() throws InvalidMapException
    {
        if (getMap().getNumberOfAvailablePoints(getHeading()) < 1)
            return new Stop();
        getMap().moveLocation(getHeading());
        return new Fly();
    }

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return On reprend le protocole ScanBeach
     */
    @Override
    public Protocol setResult(Area result)
    {
        return new ScanBeach(getMap(), getHeading(), getSense());
    }
}
