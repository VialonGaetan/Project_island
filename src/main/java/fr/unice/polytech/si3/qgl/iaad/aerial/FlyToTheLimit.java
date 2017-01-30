package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Fly jusqu'à la limite de la carte.
 *
 * @author Alexandre Clement
 * @since 30/01/2017.
 */
class FlyToTheLimit extends Oriented implements Protocol
{
    FlyToTheLimit(IslandMap map, Direction heading, Direction sense)
    {
        super(map, heading, sense);
    }

    /**
     * @return l'action Fly si on est pas à la limite de la carte, sinon Stop
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        getMap().moveLocation(getHeading());
        if (getMap().getNumberOfAvailablePoints(getHeading()) < 2)
        {
            if (getMap().getNumberOfAvailablePoints(getHeading().getLeft()) > getMap().getNumberOfAvailablePoints(getHeading().getRight()))
            {
                getMap().moveLocation(getHeading().getLeft());
                setHeading(getHeading().getLeft());
                return new Heading(getHeading());
            }
            getMap().moveLocation(getHeading().getRight());
            setHeading(getHeading().getRight());
            return new Heading(getHeading());
        }
        return new Fly();
    }

    /**
     * @param result le résultat de l'action effectué
     * @return EchoToFindIsland dans une direction de dimension non nulle
     */
    @Override
    public Protocol setResult(Area result)
    {
        if (getMap().getNumberOfAvailablePoints(getHeading().getLeft()) > 0)
            return new EchoToFindIsland(getMap(), getHeading(), getSense(), getHeading().getLeft());
        if (getMap().getNumberOfAvailablePoints(getHeading().getRight()) > 0)
            return new EchoToFindIsland(getMap(), getHeading(), getSense(), getHeading().getRight());
        return new StopAerial();
    }
}
