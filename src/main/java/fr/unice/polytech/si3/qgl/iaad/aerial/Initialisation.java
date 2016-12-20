package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 *         <p>
 *         Initialise les dimensions de la map
 */
class Initialisation implements Protocol
{
    /**
     * La carte utilisée
     */
    private IslandMap map;
    /**
     * L'orientation du drone
     */
    private Direction heading;
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
        this.map = map;
        this.heading = heading;
        protocol = new EchoToFindLimit(heading);
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
    public Action nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        return protocol = protocol.setResult(result);
    }

    /**
     * Fait un ECHO dans la direction donnée
     */
    private class EchoToFindLimit implements Protocol
    {
        private Direction direction;

        /**
         * @param direction la direction selon laquelle on veut faire un ECHO
         */
        private EchoToFindLimit(Direction direction)
        {
            this.direction = direction;
        }

        @Override
        public Action nextAction()
        {
            return new Echo(direction);
        }

        /**
         * Si on trouve l'île, on lance le protocole FlyToIsland
         * Sinon, on exécute ce protocole dans une autre direction
         * Si un ECHO a été fait dans chaque direction, on lance le protocol FindIsland
         *
         * @param result le résultat de l'action précédente
         * @return Le nouveau protocole a utilisé
         */
        @Override
        public Protocol setResult(Area result) throws InvalidMapException
        {
            if (Element.valueOf(result.getFound()) == Element.GROUND)
            {
                map.setGround(direction, result.getRange());
                return new FlyToIsland(map, heading, direction, sense, result.getRange() - 1);
            }
            if (!map.isDirectionFinished(direction))
                map.setOutOfRange(direction, result.getRange());

            if (direction == heading.getRight())
                return new FindIsland(map, heading, sense);
            if (direction == heading)
                return new EchoToFindLimit(heading.getLeft());
            return new EchoToFindLimit(heading.getRight());
        }
    }
}
