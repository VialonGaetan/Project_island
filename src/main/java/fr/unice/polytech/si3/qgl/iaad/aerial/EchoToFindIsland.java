package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Fait un ECHO de chaque coté du drone pour trouver l'île.
 *
 * @author Alexandre Clement
 * @since 30/01/2017.
 */
class EchoToFindIsland extends Oriented implements Protocol
{
    EchoToFindIsland(IslandMap map, Direction heading, Direction sense, Direction direction)
    {
        super(map, heading, sense, direction);
    }

    @Override
    public Decision nextAction()
    {
        return new Echo(getDirection());
    }

    /**
     * Fait un echo de chaque coté
     * Si c'est déjà fait, renvoie le protocole FlyToTheLimit
     *
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return le nouveau protocole en vigueur
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        if (Element.valueOf(result.getFound()) == Element.GROUND)
        {
            getMap().setGround(getDirection(), result.getRange());
            return new FlyToIsland(getMap(), getHeading(), getDirection(), getSense(), result.getRange() - 1);
        }
        if (getMap().getNumberOfAvailablePoints(getDirection().getBack()) > 0 && getDirection() != getHeading().getRight())
            return new EchoToFindIsland(getMap(), getHeading(), getSense(), getDirection().getBack());
        return new FlyToTheLimit(getMap(), getHeading(), getSense());
    }
}
