
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.islandMap.AddPointsException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;

/**
 * @author Alexandre Clement
 *         Created the 20/11/2016.
 */
public class Drone
{
    private Direction direction;
    private IslandMap islandMap;
    private Action action;
    private int cost;
    private int range;
    private ArgActions argAction;
    private String found;
    private String status;
    private String biome;
    private int numberOfEcho;
    private int numberOfFlyLeft;
    private int numberOfFlyRight;
    private int numberOfFly;
    private AreaResult result;

    public Drone(Direction heading, IslandMap islandMap)
    {
        this.direction=heading;
        this.islandMap=islandMap;
        action=new Echo(heading);
        argAction=ArgActions.ECHO;
        numberOfEcho++;
    }

    public Action doAction() { return new Stop(); }

    public void stop()
    {
        action = new Stop();
    }

    public void fly() { action = new Fly(); }


    public void getResult(AreaResult results)
    {
        this.result = results;
    }

    public void strategy()
    {
        if(numberOfEcho<=2)
        {
            action=new Echo(direction.getLeft());
            if(numberOfEcho==2) action=new Echo(direction.getRight());
            numberOfEcho++;
        }

        else if(numberOfFly<islandMap.getNumberOfAvailablePoints(direction))
        {
            if(numberOfFly%2==1) numberOfEcho=0;
            action=new Fly();
            numberOfFly++;
        }

        else if(islandMap.getNumberOfAvailablePoints(direction.getRight())>0)
        {
            direction=direction.getRight();
            action=new Stop();
        }

        else if(islandMap.getNumberOfAvailablePoints(direction.getLeft())>0)
        {
            direction=direction.getLeft();
            action=new Stop();
        }
    }

    public Direction getHeading()
    {
        return this.direction;
    }
}
