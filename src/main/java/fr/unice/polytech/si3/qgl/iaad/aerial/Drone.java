
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.islandMap.AddPointsException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
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
    private int test=0;

    public Drone(Direction heading, IslandMap islandMap, boolean start)
    {
        this.direction=heading;
        this.islandMap=islandMap;
        if(start==false) echo();
    }

    public Action doAction() { return action; }

    public void stop()
    {
        action = new Stop();
    }

    public void fly() { action = new Fly(); }

    public void echo() { action = new Echo(direction); }

    public void getResult(String results)
    {
        fly();
    }
}
