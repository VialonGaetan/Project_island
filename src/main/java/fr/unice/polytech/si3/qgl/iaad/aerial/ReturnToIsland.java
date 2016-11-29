package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
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
    ReturnToIsland(IslandMap map, Direction direction, Direction sense)
    {
        this.protocol = protocol;
        protocol = new Turn(new Initialisation(map, sense), map, direction, sense);
    }

    @Override
    public Action nextAction()
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        return protocol = protocol.setResult(result);
    }


}
