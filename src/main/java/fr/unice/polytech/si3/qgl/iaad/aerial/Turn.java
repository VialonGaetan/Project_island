package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Tourne le drone.
 * <p>
 * Created the 29/11/2016.
 *
 * @author Alexandre Clement
 */
class Turn extends Oriented implements Protocol
{
    private final Protocol protocol;

    /**
     * @param protocol le protocol a exécuté après avoir tourné le drone
     * @param heading  l'orientation du drone
     * @param target   l'orientation voulue du drone
     */
    Turn(Protocol protocol, IslandMap map, Direction heading, Direction target) throws InvalidMapException
    {
        super(map, heading, null, target);
        this.protocol = protocol;
    }

    /**
     * @return Heading dans la direction voulue
     */
    @Override
    public Decision nextAction() throws InvalidMapException
    {
        if (getMap().getNumberOfAvailablePoints(getHeading()) < 1 || getMap().getNumberOfAvailablePoints(getDirection()) < 1)
            return new Stop();
        getMap().moveLocation(getHeading());
        getMap().moveLocation(getDirection());
        return new Heading(getDirection());
    }

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return le protocol donnée
     */
    @Override
    public Protocol setResult(Area result)
    {
        return protocol;
    }
}