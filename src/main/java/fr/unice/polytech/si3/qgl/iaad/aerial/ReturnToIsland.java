package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Retourne sur l'île.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
class ReturnToIsland implements Protocol
{
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;

    /**
     * On tourne dans le sens de parcours puis on relance une Initialisation en conservant l'actuelle sens de parcours
     *
     * @param direction orientation du drone
     * @param sense     sens de parcours de l'île
     */
    ReturnToIsland(IslandMap map, Direction direction, Direction sense) throws InvalidMapException
    {
        /*
        On met en file 5 sous-protocoles:
            On vérifie qu'il n'y a pas un océan interne devant le drone (i.e il y a encore GROUND devant)
            On vérifie qu'il n'y a pas de GROUND sur le coté du sens de parcours de l'ile
            On fait demi-tour
            On relance le protocole d'initialisation
         */
        protocol = new Initialisation(map, direction.getBack(), sense);
        protocol = new Turn(protocol, map, sense, direction.getBack());
        protocol = new Turn(protocol, map, direction, sense);
        protocol = new CheckGroundOnSenseSide(protocol, map, direction, sense);
        protocol = new CheckInnerOcean(protocol, map, direction, sense);
    }

    @Override
    public Decision nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        protocol = protocol.setResult(result);
        return protocol;
    }
}
