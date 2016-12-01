package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 29/11/2016.
 * Tourne le drone
 */
public class Turn implements Protocol
{
    private Protocol protocol;
    private Direction target;

    /**
     * @param protocol le protocol a exécuté après avoir tourné le drone
     * @param heading l'orientation du drone
     * @param target l'orientation voulue du drone
     */
    Turn(Protocol protocol, IslandMap map, Direction heading, Direction target)
    {
        this.protocol = protocol;
        this.target = target;
        map.moveDrone(heading);
        map.moveDrone(target);
    }

    /**
     * @return Heading dans la direction voulue
     */
    @Override
    public Action nextAction() {
        return new Heading(target);
    }

    /**
     * @param result le résultat de l'action effectué
     * @return le protocol donnée
     */
    @Override
    public Protocol setResult(AreaResult result) {
        return protocol;
    }
}