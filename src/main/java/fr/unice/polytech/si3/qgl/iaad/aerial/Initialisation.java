package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Initialise les dimensions de la islandMap.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
class Initialisation implements Protocol
{
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;
    /**
     * Conservation du sens de parcours de la carte pour l'exploration de l'île
     */
    private Direction sense;

    /**
     * Initialise le drone dans la carte
     *
     * @param map     la carte utilisée par le drone
     * @param heading l'orientation du drone
     */
    Initialisation(IslandMap map, Direction heading)
    {
        protocol = new EchoToFindLimit(map, heading, sense, heading);
    }

    /**
     * Re-initialise le drone avec conservation du précédent sens de parcours de la carte
     *
     * @param map     la carte utilisée par le drone
     * @param heading l'orientation du drone
     * @param sense   le sens de parcours de la carte si on a déjà parcourus la carte
     */
    Initialisation(IslandMap map, Direction heading, Direction sense)
    {
        this(map, heading);
        this.sense = sense;
    }

    @Override
    public Decision nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        return protocol.setResult(result);
    }
}
