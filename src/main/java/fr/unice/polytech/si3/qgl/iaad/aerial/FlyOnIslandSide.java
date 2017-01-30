package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Le Drone longe les côtes de l'île.
 * <p>
 * Empêche de faire demi-tour dans le milieu d'une côte.
 *
 * @author Alexandre Clement
 * @since 30/01/2017
 */
class FlyOnIslandSide extends Oriented implements Protocol
{
    /**
     * Le protocole a faire après avoir exécuté celui-ci
     */
    private Protocol exit;

    /**
     * @param exit    le protocole a faire après avoir exécuté celui-ci
     * @param heading l'orientation du drone
     */
    FlyOnIslandSide(Protocol exit, IslandMap map, Direction heading)
    {
        super(map, heading);
        this.exit = exit;
    }

    /**
     * @return Fly dans l'orientation du drone
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        getMap().moveLocation(getHeading());
        return new Fly();
    }

    /**
     * @param result le résultat de l'action effectué
     * @return le protocole de sortie
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        return exit;
    }
}
