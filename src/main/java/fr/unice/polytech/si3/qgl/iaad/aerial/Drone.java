
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.islandMap.AddPointsException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import org.json.JSONObject;

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

    public Drone(Direction heading, IslandMap islandMap)
    {
        this.direction=heading;
        this.islandMap=islandMap;
        action=new Echo(heading);
        argAction=ArgActions.ECHO;
        numberOfEcho++;
    }

    public Action doAction() { return action; }

    public void getResult(String result)
    {
        JSONObject jsonObject=new JSONObject(result);

        if(jsonObject.has("cost")) cost=jsonObject.getInt("cost");
        if(jsonObject.has("status")) status=jsonObject.getString("status");

        switch(argAction)
        {
            case ECHO:
                if(jsonObject.has("extras"))
                {
                    range=jsonObject.getJSONObject("extras").getInt("range");
                    found=jsonObject.getJSONObject("extras").getString("found");
                    if(found.equals("GROUND")) islandMap.ground(direction, range);
                    else islandMap.setOutOfRange(direction, range);
                }
                break;
            case SCAN:
                if(jsonObject.has("extras"))
                {
                    biome=jsonObject.getJSONObject("extras").getString("creeks");
                    if(biome!=null) islandMap.setElement(Element.CREEK);
                    else if(biome.equals("BEACH")) islandMap.setElement(Element.BEACH);
                }
                break;
            case FLY:
                islandMap.moveDroneCorrectly(direction);
                break;
        }

        strategy();
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
