package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ScanResultat;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.strategy.simple.ExploreTuile;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;
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
        ScanResultat scanResultat = new ScanResultat(result);

        acknowledgeMap(scanResultat);

        if (droneHasEnoughExplored())
        {
            Optional<Point> creekLocation = findCreekLocation();

            if (creekLocation.isPresent())
            {
                return land(creekLocation.get());
            }
        }

        if (scanResultat.getBiomes().contains(Biome.OCEAN) && scanResultat.getBiomes().size() == 1)
            return new ReturnToIsland(context, islandMap, drone, direction);

        return new FlyOnMap(new ScanIsland(context, islandMap, drone, direction), islandMap, drone);
    }

    private Protocol land(Point creekLocation)
    {
        Creek creek = islandMap.getTile(creekLocation).getCreeks().get(0);
        islandMap.zoom();
        Crew crew = new Crew(1, aerialToGroundLocation(creekLocation));
        Protocol groundStrategy = new ExploreTuile(retrieveContractFromContext(context), crew, islandMap);
        return new LandOnCreek(groundStrategy, creek, 1);
    }

    private boolean droneHasEnoughExplored()
    {
        Drone simulation = new Drone(drone);
        simulation.heading(direction);
        return islandMap.isOnMap(simulation.getLocation()) && islandMap.getTile(simulation.getLocation()).isAlready(GroundActionTile.SCANNED);
    }

    private void acknowledgeMap(ScanResultat scanResultat)
    {
        Tile tile = islandMap.getTile(drone.getLocation());
        tile.setAsAlready(GroundActionTile.SCANNED);
        tile.addBiomes(scanResultat.getBiomes());
        tile.addCreeks(scanResultat.getCreeks());
        tile.addEmergencySites(scanResultat.getSites());
    }

    private static Map<Resource, Integer> retrieveContractFromContext(Context context)
    {
        Map<Resource, Integer> contracts = new EnumMap<>(Resource.class);

        for (Contract contract : context.getContracts())
        {
            contracts.put(contract.getResource(), contract.getAmount());
        }
        return contracts;
    }

}
