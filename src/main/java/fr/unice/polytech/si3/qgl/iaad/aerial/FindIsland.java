package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;

/**
 * Cherche l'île si on ne la trouve pas lors de l'Initialisation.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
class FindIsland implements Protocol
{
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;

    /**
     * @param map     la carte actuelle
     * @param heading l'orientation du drone
     * @param sense   le sens de parcours
     */
    FindIsland(IslandMap map, Direction heading, Direction sense)
    {
        protocol = new EchoToFindIsland(map, heading, sense, heading.getLeft());
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
