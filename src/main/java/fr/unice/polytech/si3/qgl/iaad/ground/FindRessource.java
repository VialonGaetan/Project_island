package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.*;

import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.util.HashMap;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class FindRessource implements ProtocolGround {
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
    private ProtocolGround protocol;
    /**
     * Conservation du sens de parcours de la carte pour l'exploration de l'île
     */
    private Direction sense;

    private HashMap<Resource,Integer> contrat;

    /**
     * @param map     la carte actuelle
     * @param heading l'orientation du drone
     * @param sense   le sens de parcours
     */
    FindRessource(IslandMap map, Direction heading, Direction sense)
    {
        this.map = map;
        this.heading = heading;
        //protocol = new FindIsland.EchoToFindIsland(heading.getLeft());
        this.sense = sense;
    }

    @Override
    public Action nextAction()
    {
        return protocol.nextAction();
    }

    @Override
    public ProtocolGround setResult(Ground result)
    {
        return protocol = protocol.setResult(result);
    }


    /**
     * Fait un ECHO de chaque coté du drone pour trouver l'île
     */
    private class GlimpseToFindRessource implements ProtocolGround
    {
        private Direction direction;
        private int range;

        private GlimpseToFindRessource(Direction direction, int range)
        {
            this.direction = direction;
            this.range = range;
        }

        @Override
        public Action nextAction()
        {
            return new Glimpse(direction,range);
        }

        /**
         * Fait un echo de chaque coté
         * Si c'est déjà fait, renvoie le protocole FlyToTheLimit
         *
         * @param result le résultat de l'action effectué
         * @return le nouveau protocole en vigueur
         */
        @Override
        public ProtocolGround setResult(Ground result)
        {
            /*
            if (Element.valueOf(result.getFound()) == Element.GROUND)
            {
                map.setGround(direction, result.getRange());
                return new FlyToIsland(map, heading, direction, sense, result.getRange() - 1);
            }
            if (map.getNumberOfAvailablePoints(direction.getBack()) > 0 && direction != heading.getRight())
                return new FindIsland.EchoToFindIsland(direction.getBack());
            return new FindIsland.FlyToTheLimit();
            */
            return new StopExplorer();
        }
    }
}
