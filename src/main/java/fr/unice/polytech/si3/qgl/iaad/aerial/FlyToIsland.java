package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Vol jusqu'à l'île.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
class FlyToIsland extends Oriented implements Protocol
{
    private int range;

    /**
     * @param heading l'orientation du drone
     * @param direction  la direction vers laquelle se trouve l'île
     * @param range   la distance a parcourir pour atteindre l'île
     */
    FlyToIsland(IslandMap map, Direction heading, Direction direction, Direction sense, int range)
    {
        super(map, heading, sense, direction);
        this.range = range;
    }

    /**
     * @return getHeading() si le drone n'est pas dans la bonne direction
     * sinon Fly
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        range -= 1;
        if (getMap().getNumberOfAvailablePoints(getHeading()) < 1)
            return new Stop();
        getMap().moveLocation(getHeading());
        if (getHeading() == getDirection())
            return new Fly();
        if (getMap().isDirectionFinished(getDirection()) && getMap().getNumberOfAvailablePoints(getDirection()) < 1)
            return new Stop();
        if (getSense() == null)
            setSense(getHeading());
        setHeading(getDirection());
        getMap().moveLocation(getDirection());
        return new Heading(getDirection());
    }

    /**
     * @param result le résultat de l'action effectué
     * @return ScanIsland si on a atteint l'île
     * FlyToIsland sinon
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        if (getSense() == null)
            setSense(getHeading().getRight());
        if (range > 0)
            return this;
        if (getSense() == getHeading())
            return new Turn(new ScanIsland(getMap(), getHeading().getRight(), getSense()), getMap(), getHeading(), getHeading().getRight());
        if (getSense().getBack() == getHeading())
            return new Turn(new ScanIsland(getMap(), getHeading().getRight(), getSense().getBack()), getMap(), getHeading(), getHeading().getRight());
        if (range == 0)
            return this;
        if (Drone.searchEmergencySite)
            return new ScanIsland(getMap(), getHeading(), getSense());
        return new ScanBeach(getMap(), getHeading(), getSense());
    }
}
