package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;

/**
 * Vérifie que l'on est pas dans un océan à l'intérieur de l'île
 * i.e il n'y a pas de GROUND devant le drone.
 *
 * @author Alexandre Clement
 * @since 30/01/2017
 */
class CheckInnerOcean extends Oriented implements Protocol
{
    /**
     * Le protocole a faire après avoir exécuté celui-ci
     */
    private Protocol exit;

    /**
     * @param exit    le protocole a faire après avoir exécuté celui-ci
     * @param heading l'orientation du drone
     * @param sense   le sens de parcours de l'île
     */
    CheckInnerOcean(Protocol exit, IslandMap map, Direction heading, Direction sense)
    {
        super(map, heading, sense);
        this.exit = exit;
    }

    /**
     * @return ECHO devant le drone
     */
    @Override
    public Decision nextAction()
    {
        return new Echo(getHeading());
    }

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return FlyToIsland si il y a GROUND devant le drone
     * Le Protocol exit sinon
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        if (Element.valueOf(result.getFound()) == Element.GROUND)
            return new FlyToIsland(getMap(), getHeading(), getHeading(), getSense(), result.getRange());
        return exit;
    }
}
