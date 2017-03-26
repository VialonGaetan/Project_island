package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.aerial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ScanResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial.ScheduleCrewPath;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.Tile;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.awt.*;
import java.util.Optional;

/**
 * @author Alexandre Clement
 * @since 04/03/2017.
 */

public class ScanIsland extends Aerial implements Protocol
{
    private final Compass direction;

    ScanIsland(Context context, IslandMap islandMap, Drone drone, Compass direction)
    {
        super(context, islandMap, drone);
        this.direction = direction;
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

        acknowledgeMap(scanResult);

        if (droneHasEnoughExplored())
        {
            Optional<Point> creekLocation = findCreekLocation();

            if (creekLocation.isPresent())
            {
                return land(creekLocation.get());
            }
        }

        if (scanResult.getBiomes().contains(Biome.OCEAN) && scanResult.getBiomes().size() == 1)
            return new ReturnToIsland(context, islandMap, drone, direction);

        return new FlyOnMap(new ScanIsland(context, islandMap, drone, direction), islandMap, drone);
    }

    private Protocol land(Point creekLocation)
    {
        islandMap.zoom();
        Crew crew = new Crew(1, aerialToGroundLocation(creekLocation));
        Protocol groundStrategy = new ScheduleCrewPath(context, islandMap, crew);
        return new LandOnCreek(groundStrategy, islandMap, aerialToGroundLocation(creekLocation), crew);
    }

    private boolean droneHasEnoughExplored()
    {

        Drone simulation = new Drone(drone);
        simulation.heading(direction);
        return islandMap.isOnMap(simulation.getLocation()) && islandMap.getTile(simulation.getLocation()).isAlready(GroundActionTile.SCANNED);
    }

    private void acknowledgeMap(ScanResult scanResult)
    {
        Tile tile = islandMap.getTile(drone.getLocation());
        tile.setAsAlready(GroundActionTile.SCANNED);
        tile.addBiomes(scanResult.getBiomes());
        tile.addCreeks(scanResult.getCreeks());
        tile.addEmergencySites(scanResult.getSites());
    }
}
