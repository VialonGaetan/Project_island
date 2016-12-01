package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 * Initialise les dimensions de la map
 */
public class Initialisation implements Protocol
{
    private Direction heading;
    private Protocol protocol;
    private Direction sense;
    private IslandMap map;

    Initialisation(IslandMap map, Direction heading)
    {
        this.map = map;
        this.heading = heading;
        protocol = new EchoToFindLimit(heading);
    }

    Initialisation(IslandMap map, Direction heading, Direction sense)
    {
        this(map, heading);
        this.sense = sense;
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

    /**
     * Fait un echo dans la direction donnée
     */
    private class EchoToFindLimit implements Protocol
    {
        private Direction direction;

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
         * @param result le résultat de l'action précédente
         * @return Le nouveau protocole a utilisé
         */
        @Override
        public Protocol setResult(AreaResult result)
        {
            if (Element.valueOf(result.getFound()) == Element.GROUND)
            {
                map.setGround(direction, result.getRange());
                return new FlyToIsland(map, heading, direction, sense, result.getRange());
            }
            map.setOutOfRange(direction, result.getRange());

            if (direction == heading.getRight())
                return new FindIsland(map, heading, sense);
            if (direction == heading)
                return new EchoToFindLimit(heading.getLeft());
            return new EchoToFindLimit(heading.getRight());
        }
    }
}
