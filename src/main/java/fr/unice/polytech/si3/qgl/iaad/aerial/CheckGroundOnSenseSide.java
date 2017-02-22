package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;

/**
 * Vérifie qu'il n'y a pas de GROUND dans le sens de parcours du drone.
 *
 * @author Alexandre Clement
 * @since 30/01/2017.
 */
class CheckGroundOnSenseSide extends Oriented implements Protocol
{
    /**
     * Le protocole a faire après avoir exécuté celui-ci
     */
    private Protocol exit;

    /**
     * @param exit    le protocole de sortie
     * @param heading l'orientation du drone
     * @param sense   le sens de parcours de l'île par le drone
     */
    CheckGroundOnSenseSide(Protocol exit, IslandMap map, Direction heading, Direction sense)
    {
        super(map, heading, sense);
        this.exit = exit;
    }

    /**
     * @return ECHO dans le sens de parcours de l'île par le drone
     */
    @Override
    public Decision nextAction() throws InvalidMapException
    {
        return new Echo(getSense());
    }

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return Le protocole exit si il n'y a pas de GROUND sur le coté sense du drone
     * FlyOnIslandSide sinon
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        if (Element.valueOf(result.getFound()) != Element.GROUND || result.getRange() > 1)
            return exit;
        else if (!getMap().isDirectionFinished(getHeading()))
            getMap().setOutOfRange(getHeading(), result.getRange());
        if (getMap().getNumberOfAvailablePoints(getHeading()) < 1)
            return new StopAerial();
        return new FlyOnIslandSide(this, getMap(), getHeading());
    }
}
