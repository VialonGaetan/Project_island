package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.util.HashMap;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class InitialisationGround implements ProtocolGround{

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

    InitialisationGround()
    {
        protocol = new ExploreToFindRessource();
    }
    /**
     * Initialise le drone dans la carte
     *
     * @param contrat     la carte utilisée par le drone
     * @param heading l'orientation du drone
     */
    InitialisationGround(HashMap contrat, Direction heading)
    {
        this.map = map;
        this.heading = heading;
        protocol = new ExploreToFindRessource();
    }

    /**
     * Re-initialise le drone avec conservation du précédent sens de parcours de la carte
     *
     * @param map     la carte utilisée par le drone
     * @param heading l'orientation du drone
     * @param sense   le sens de parcours de la carte si on a déjà parcourus la carte
     */
    InitialisationGround(HashMap map, Direction heading, Direction sense)
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
    public ProtocolGround setResult(Ground result) throws InvalidMapException
    {
        return protocol = protocol.setResult(result);
    }

    /**
     * Fait un ECHO dans la direction donnée
     */
    private class ExploreToFindRessource implements ProtocolGround
    {
        private Direction direction;


        private ExploreToFindRessource()
        {
        }

        @Override
        public Action nextAction()
        {
            return new Explore();
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
        public ProtocolGround setResult(Ground result) throws InvalidMapException
        {
            for (int i = 0; result.getRessourceExplore(i) != null ; i++) {
                if (Resource.valueOf(result.getRessourceExplore(i)) == Resource.FUR)
                {
                    return new ExploitResource(Resource.valueOf(result.getRessourceExplore(i)),3);
                }
            }
            return new StopExplorer();
        }
    }
}
