
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;

/**
 * @author Alexandre Clement
 *         Created the 20/11/2016.
 */
public class Drone
{
    private static final int LOW_BUDGET = 200;

    private Action action;
    public Direction direction;
    private IslandMap islandMap;
    private Results result;
    private Protocol protocol;
    private int budget;


    public Drone(int budget, Direction heading, IslandMap islandMap)
    {
        this.direction=heading;
        this.islandMap=islandMap;
        this.budget = budget;
        protocol = new Initialisation(heading);
    }

    public Action doAction()
    {
        if (budget < LOW_BUDGET)
            return new Stop();
        return action = protocol.nextAction();
    }

    public void getResult(AreaResult results)
    {
        protocol = protocol.setResult(results);
    }
}
