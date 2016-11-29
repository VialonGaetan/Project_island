package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 * Cherche l'île si on ne la trouve pas lors de l'Initialisation
 */
public class FindIsland implements Protocol
{
    private IslandMap map;
    private Direction heading;
    private Protocol protocol;

    /**
     * @param heading l'orientation du drone
     */
    FindIsland(IslandMap map, Direction heading)
    {
        this.map = map;
        this.heading = heading;
        protocol = new EchoToFindIsland(heading.getLeft());
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
     * Fait un ECHO de chaque coté du drone pour trouver l'île
     */
    private class EchoToFindIsland implements Protocol
    {
        private Direction direction;

        private EchoToFindIsland(Direction direction)
        {
            this.direction = direction;
        }

        @Override
        public Action nextAction()
        {
            return new Echo(direction);
        }

        /**
         * Fait un echo de chaque coté
         * Si c'est déjà fait, renvoie le protocole FlyToTheLimit
         * @param result le résultat de l'action effectué
         * @return le nouveau protocole en vigueur
         */
        @Override
        public Protocol setResult(AreaResult result)
        {
            if (Element.valueOf(result.getFound()) == Element.GROUND)
            {
                map.ground(direction, result.getRange());
                return new FlyToIsland(map, heading, direction, result.getRange());
            }
            if (map.getNumberOfAvailablePoints(direction.getBack()) > 0 && direction != heading.getRight())
                return new EchoToFindIsland(direction.getBack());
            return new FlyToTheLimit();
        }
    }

    /**
     * Fly jusqu'à la limite de la carte
     */
    private class FlyToTheLimit implements Protocol
    {
        /**
         * @return l'action Fly si on est pas à la limite de la carte, sinon Stop
         */
        @Override
        public Action nextAction()
        {
            if (map.getNumberOfAvailablePoints(heading) < 2)
                return new Stop();
            map.moveDroneCorrectly(heading);
            return new Fly();
        }

        /**
         * @param result le résultat de l'action effectué
         * @return EchoToFindIsland dans une direction de dimension non nulle
         */
        @Override
        public Protocol setResult(AreaResult result)
        {
            if (map.getNumberOfAvailablePoints(heading.getLeft()) > 0)
                return new EchoToFindIsland(heading.getLeft());
            if (map.getNumberOfAvailablePoints(heading.getRight()) > 0)
                return new EchoToFindIsland(heading.getRight());
            return new Land();
        }
    }
}
