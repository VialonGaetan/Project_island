package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Fait un ECHO dans la direction donnée.
 *
 * @author Alexandre Clement
 * @since 30/01/2017.
 */

class EchoToFindLimit extends Oriented implements Protocol
{
    /**
     * @param direction la direction selon laquelle on veut faire un ECHO
     */
    EchoToFindLimit(IslandMap map, Direction heading, Direction sense, Direction direction)
    {
        super(map, heading, sense, direction);
    }

    @Override
    public Action nextAction()
    {
        return new Echo(getDirection());
    }

    /**
     * Si on trouve l'île, on lance le protocole FlyToIsland
     * Sinon, on exécute ce protocole dans une autre direction
     * Si un ECHO a été fait dans chaque direction, on lance le protocol FindIsland
     *
     * @param result le résultat de l'action précédente
     * @return Le nouveau protocole a utilisé
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        if (Element.valueOf(result.getFound()) == Element.GROUND)
        {
            getMap().setGround(getDirection(), result.getRange());
            return new FlyToIsland(getMap(), getHeading(), getDirection(), getSense(), result.getRange() - 1);
        }
        if (!getMap().isDirectionFinished(getDirection()))
            getMap().setOutOfRange(getDirection(), result.getRange());

        if (getDirection() == getHeading().getRight())
            return new FindIsland(getMap(), getHeading(), getSense());
        if (getDirection() == getHeading())
            return new EchoToFindLimit(getMap(), getHeading(), getSense(), getHeading().getLeft());
        return new EchoToFindLimit(getMap(), getHeading(), getSense(), getHeading().getRight());
    }
}
