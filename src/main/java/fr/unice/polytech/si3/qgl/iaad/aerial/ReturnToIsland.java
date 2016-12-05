package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 * Retourne sur l'île
 */
public class ReturnToIsland implements Protocol
{

    private Protocol protocol;

    /**
     * todo improve this behaviour
     * On tourne dans le sens de parcours puis on reprend à Initialisation
     * @param direction orientation du drone
     * @param sense sens de parcours de l'île
     */
    ReturnToIsland(IslandMap map, Direction direction, Direction sense) throws InvalidMapException
    {
        protocol = new Initialisation(map, direction, sense);
        protocol = new Turn(protocol, map, sense, direction);
        protocol = new Turn(protocol, map, direction.getBack(), sense);
    }

    @Override
    public Action nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        return protocol = protocol.setResult(result);
    }


}
