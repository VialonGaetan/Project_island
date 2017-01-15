package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Retourne sur l'île.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
class ReturnToIsland implements Protocol
{
    /**
     * La carte utilisée
     */
    private IslandMap map;
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;

    /**
     * On tourne dans le sens de parcours puis on relance une Initialisation en conservant l'actuelle sens de parcours
     *
     * @param direction orientation du drone
     * @param sense     sens de parcours de l'île
     */
    ReturnToIsland(IslandMap map, Direction direction, Direction sense) throws InvalidMapException
    {
        this.map = map;
        /*
        On met en file 5 sous-protocoles:
            On vérifie qu'il n'y a pas un océan interne devant le drone (i.e il y a encore GROUND devant)
            On vérifie qu'il n'y a pas de GROUND sur le coté du sens de parcours de l'ile
            On fait demi-tour
            On relance le protocole d'initialisation
         */
        protocol = new Initialisation(map, direction.getBack(), sense);
        protocol = new Turn(protocol, map, sense, direction.getBack());
        protocol = new Turn(protocol, map, direction, sense);
        protocol = new CheckGroundOnSenseSide(protocol, direction, sense);
        protocol = new CheckInnerOcean(protocol, direction, sense);
    }

    @Override
    public Action nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        protocol = protocol.setResult(result);
        return protocol;
    }

    /**
     * Vérifie que l'on est pas dans un océan à l'intérieur de l'île
     * i.e il n'y a pas de GROUND devant le drone
     */
    private class CheckInnerOcean implements Protocol
    {
        /**
         * Le protocole a faire après avoir exécuté celui-ci
         */
        private Protocol exit;
        /**
         * L'orientation du drone
         */
        private Direction heading;
        /**
         * Le sens de parcours de l'île
         */
        private Direction sense;

        /**
         * @param exit    le protocole a faire après avoir exécuté celui-ci
         * @param heading l'orientation du drone
         * @param sense   le sens de parcours de l'île
         */
        private CheckInnerOcean(Protocol exit, Direction heading, Direction sense)
        {
            this.exit = exit;
            this.heading = heading;
            this.sense = sense;
        }

        /**
         * @return ECHO devant le drone
         */
        @Override
        public Action nextAction()
        {
            return new Echo(heading);
        }

        /**
         * @param result le résultat de l'action effectué
         * @return FlyToIsland si il y a GROUND devant le drone
         * Le Protocol exit sinon
         */
        @Override
        public Protocol setResult(Area result) throws InvalidMapException
        {
            if (Element.valueOf(result.getFound()) == Element.GROUND)
                return new FlyToIsland(map, heading, heading, sense, result.getRange());
            return exit;
        }
    }

    /**
     * Vérifie qu'il n'y a pas de GROUND dans le sens de parcours du drone
     */
    private class CheckGroundOnSenseSide implements Protocol
    {
        /**
         * Le protocole a faire après avoir exécuté celui-ci
         */
        private Protocol exit;
        /**
         * L'orientation du drone
         */
        private Direction heading;
        /**
         * Le sens de parcours de l'île
         */
        private Direction sense;

        /**
         * @param exit    le protocole de sortie
         * @param heading l'orientation du drone
         * @param sense   le sens de parcours de l'île par le drone
         */
        private CheckGroundOnSenseSide(Protocol exit, Direction heading, Direction sense)
        {
            this.exit = exit;
            this.heading = heading;
            this.sense = sense;
        }

        /**
         * @return ECHO dans le sens de parcours de l'île par le drone
         */
        @Override
        public Action nextAction() throws InvalidMapException
        {
            return new Echo(sense);
        }

        /**
         * @param result le résultat de l'action effectué
         * @return Le protocole exit si il n'y a pas de GROUND sur le coté sense du drone
         * FlyOnIslandSide sinon
         */
        @Override
        public Protocol setResult(Area result) throws InvalidMapException
        {
            if (Element.valueOf(result.getFound()) != Element.GROUND || result.getRange() > 1)
                return exit;
            else if (!map.isDirectionFinished(heading))
                map.setOutOfRange(heading, result.getRange());
            if (map.getNumberOfAvailablePoints(heading) < 1)
                return new StopAerial();
            return new FlyOnIslandSide(this, heading);
        }
    }

    /**
     * Le Drone longe les côtes de l'île
     * <p>
     * Empêche de faire demi-tour dans le milieu d'une côte
     */
    private class FlyOnIslandSide implements Protocol
    {
        /**
         * Le protocole a faire après avoir exécuté celui-ci
         */
        private Protocol exit;
        /**
         * L'orientation du drone
         */
        private Direction direction;

        /**
         * @param exit      le protocole de sortie
         * @param direction l'orientation du drone
         */
        private FlyOnIslandSide(Protocol exit, Direction direction)
        {
            this.exit = exit;
            this.direction = direction;
        }

        /**
         * @return Fly dans l'orientation du drone
         */
        @Override
        public Action nextAction() throws InvalidMapException
        {
            map.moveLocation(direction);
            return new Fly();
        }

        /**
         * @param result le résultat de l'action effectué
         * @return le protocole de sortie
         */
        @Override
        public Protocol setResult(Area result) throws InvalidMapException
        {
            return exit;
        }
    }
}
