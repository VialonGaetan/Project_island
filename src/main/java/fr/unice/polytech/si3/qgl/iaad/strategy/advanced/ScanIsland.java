package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ScanResultat;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.Aerial;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.FlyOnMap;
import fr.unice.polytech.si3.qgl.iaad.strategy.common.LandOnCreek;
import fr.unice.polytech.si3.qgl.iaad.strategy.simple.ExploreTuile;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.map.Tile;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alexandre Clement
 * @since 04/03/2017.
 */
public class ScanIsland extends Aerial implements Protocol {
    private final Compass direction;

    ScanIsland(Context context, IslandMap islandMap, Drone drone, Compass direction) {
        super(context, islandMap, drone);
        this.direction = direction;
    }

    @Override
    public Decision takeDecision() {
        return new Scan();
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        ScanResultat scanResultat = new ScanResultat(result);

        acknowledgeMap(scanResultat);

        if (droneHasEnoughExplored()) {
            Optional<Creek> creekOptional = findACreek();
            if (creekOptional.isPresent()) {
                islandMap.zoom();
                Map<Resource, Integer> contract = new HashMap<>();
                for (Contract contrat : context.getContracts()) {
                    contract.put(contrat.getResource(), contrat.getAmount());
                }
                Protocol groundStrategy = new ExploreTuile(contract, new Crew(context.getNumberOfMen() - 1, drone.getLocation()), islandMap);
                return new LandOnCreek(groundStrategy, creekOptional.get(), 1);
            }
        }

        if (scanResultat.getBiomes().contains(Biome.OCEAN) && scanResultat.getBiomes().size() == 1)
            return new ReturnToIsland(context, islandMap, drone, direction);

        return new FlyOnMap(new ScanIsland(context, islandMap, drone, direction), islandMap, drone);
    }

    private boolean droneHasEnoughExplored() {
        Drone simulation = new Drone(drone);
        simulation.heading(direction);
        return islandMap.isOnBoard(simulation.getLocation()) && islandMap.getTile(simulation.getLocation()).isAlreadyScanned();
    }

    private void acknowledgeMap(ScanResultat scanResultat) {
        Tile tile = islandMap.getTile(drone.getLocation());
        tile.setAsAlreadyScanned();
        tile.addBiomes(scanResultat.getBiomes());
        tile.addCreeks(scanResultat.getCreeks());
        tile.addEmergencySites(scanResultat.getSites());
    }

}
