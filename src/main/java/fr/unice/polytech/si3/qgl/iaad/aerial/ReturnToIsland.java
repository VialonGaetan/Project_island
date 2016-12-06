package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
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
    private IslandMap map;

    /**
     * todo improve this behaviour
     * On tourne dans le sens de parcours puis on reprend à Initialisation
     *
     * @param direction orientation du drone
     * @param sense     sens de parcours de l'île
     */
    ReturnToIsland(IslandMap map, Direction direction, Direction sense) throws InvalidMapException
    {
        this.map = map;
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
    public Protocol setResult(AreaResult result) throws InvalidMapException
    {
        return protocol = protocol.setResult(result);
    }

    private class CheckInnerOcean implements Protocol
    {
        private Protocol exit;
        private Direction heading;
        private Direction sense;

        private CheckInnerOcean(Protocol exit, Direction heading, Direction sense)
        {
            this.exit = exit;
            this.heading = heading;
            this.sense = sense;
        }

        @Override
        public Action nextAction() throws InvalidMapException
        {
            return new Echo(heading);
        }

        @Override
        public Protocol setResult(AreaResult result) throws InvalidMapException
        {
            if (Element.valueOf(result.getFound()) == Element.GROUND)
                return new FlyToIsland(map, heading, heading, sense, result.getRange());
            return exit;
        }
    }

    private class CheckGroundOnSenseSide implements Protocol
    {
        private Protocol exit;
        private Direction heading;
        private Direction sense;

        private CheckGroundOnSenseSide(Protocol exit, Direction heading, Direction sense)
        {
            this.exit = exit;
            this.heading = heading;
            this.sense = sense;
        }

        @Override
        public Action nextAction() throws InvalidMapException
        {
            return new Echo(sense);
        }

        @Override
        public Protocol setResult(AreaResult result) throws InvalidMapException
        {
            if (Element.valueOf(result.getFound()) != Element.GROUND || result.getRange() > 1)
                return exit;
            else if(!map.isDirectionFinished(heading))
                map.setOutOfRange(heading, result.getRange());
            if (map.getNumberOfAvailablePoints(heading) < 1)
                return new Land();
            return new FlyOnIslandSide(this, heading);
        }
    }

    private class FlyOnIslandSide implements Protocol
    {
        private Protocol exit;
        private Direction direction;

        private FlyOnIslandSide(Protocol exit, Direction direction)
        {
            this.exit = exit;
            this.direction = direction;
        }

        @Override
        public Action nextAction() throws InvalidMapException
        {
            map.moveDrone(direction);
            return new Fly();
        }

        @Override
        public Protocol setResult(AreaResult result) throws InvalidMapException
        {
            return exit;
        }
    }
}
