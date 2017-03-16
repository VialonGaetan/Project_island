package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.*;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.EchoResultat;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Alexandre Clement
 * @since 02/03/2017.
 */
public class FindLimit extends Aerial implements Protocol
{
    private final Protocol exit;
    private final Compass direction;
    private final Deque<Decision> decisions;

    FindLimit(Protocol exit, Context context, IslandMap islandMap, Drone drone, Compass direction)
    {
        super(context, islandMap, drone);
        this.direction = direction;
        this.exit = exit;
        this.decisions = new ArrayDeque<>();
    }

    @Override
    public Decision takeDecision()
    {
        if (decisions.isEmpty())
            setupDecisions();
        return decisions.peek();
    }

    private void setupDecisions()
    {
        if (direction == drone.getHeading())
            turn();
        else
            fly();

        decisions.add(new Echo(direction));
    }

    private void fly()
    {
        if (droneIsAbleToFly())
        {
            drone.fly();
            decisions.add(new Fly());
        }
        else if (droneIsAbleToUTurnInDirection(getLargestSide()))
        {
            Compass heading = drone.getHeading().get(Direction.BACK);
            drone.heading(getLargestSide());
            decisions.add(new Heading(drone.getHeading()));

            drone.heading(heading);
            decisions.add(new Heading(drone.getHeading()));
        }
        else
            decisions.add(new Stop());
    }

    private void turn()
    {
        if (droneIsAbleToTurnInDirection(getLargestSide().get(Direction.BACK)))
        {
            drone.heading(getLargestSide().get(Direction.BACK));
            decisions.add(new Heading(drone.getHeading()));
        }
        else if (droneIsAbleToTurnInDirection(getLargestSide()))
        {
            drone.heading(getLargestSide());
            decisions.add(new Heading(drone.getHeading()));
        }
        else
            decisions.add(new Stop());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        decisions.pop();

        if (!decisions.isEmpty())
            return this;

        EchoResultat echoResultat = new EchoResultat(result);

        if (echoResultat.getFound() == Element.GROUND)
            return new FindLimit(exit, context, islandMap, drone, direction);

        islandMap.grow(direction, echoResultat.getRange() + distanceToCenter(direction) - islandMap.getDimension(direction));
        return exit;
    }
}
