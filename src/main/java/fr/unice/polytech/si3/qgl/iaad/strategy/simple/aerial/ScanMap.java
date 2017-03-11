package fr.unice.polytech.si3.qgl.iaad.strategy.simple.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ScanResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Turn;
import fr.unice.polytech.si3.qgl.iaad.strategy.simple.ground.ExploreTuile;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
class ScanMap implements Protocol
{
    private final Context context;
    private final IslandMap islandMap;
    private final Drone drone;
    private final Compass sense;

    ScanMap(Context context, IslandMap islandMap, Drone drone, Compass sense)
    {
        this.context = context;
        this.islandMap = islandMap;
        this.drone = drone;
        this.sense = sense;
    }

    @Override
    public Decision takeDecision()
    {
        return new Scan();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        ScanResult scanResult = new ScanResult(result);
        Compass heading = drone.getHeading();

        if (!scanResult.getCreeks().isEmpty())
        {
            // Remplacer "StopExploration" par la stratégie terrestre.
            islandMap.zoom();
            Map<Resource,Integer> contract = new HashMap<>();
            for (Contract contrat : context.getContracts()) {
                contract.put(contrat.getResource(),contrat.getAmount());
            }
            Protocol groundStrategy = new ExploreTuile(Compass.N,contract,new Crew(context.getNumberOfMen() - 1,drone.getLocation()),islandMap);
            return new LandOnCreek(groundStrategy, scanResult.getCreeks().get(0), context.getNumberOfMen() - 1);
        }

        if (droneIsAbleToFlyInDirection(heading))
            return new FlyOnMap(this, islandMap, drone);

        if (droneIsAbleToFlyInDirection(sense))
            return new Turn(new Turn(this, islandMap, drone, heading.get(Direction.BACK)), islandMap, drone, sense);

        Protocol exit = new ScanMap(context, islandMap, drone, sense.get(Direction.BACK));
        exit = new Turn(exit, islandMap, drone, heading.get(Direction.BACK));
        exit = new FlyOnMap(exit, islandMap, drone);
        return new Turn(exit, islandMap, drone, sense.get(Direction.BACK));
    }

    private boolean droneIsAbleToFlyInDirection(Compass direction)
    {
        Point location = drone.getLocation();
        Point vector = direction.getVector();
        location.translate(vector.x, vector.y);
        location.translate(vector.x, vector.y);
        return islandMap.isOnMap(location);
    }
}
