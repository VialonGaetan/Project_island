package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ResourceContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Alexandre Clement
 * @since 22/03/2017.
 */
public class Prioritisation
{
    private final Contract contract;
    private final IslandMap map;
    private final SimulatedMap simulatedMap;
    private final Area area;
    private final Crew crew;

    public Prioritisation(Contract contract, IslandMap islandMap, SimulatedMap simulatedMap, Area area, Crew crew)
    {
        this.contract = contract;
        this.map = islandMap;
        this.simulatedMap = simulatedMap;
        this.area = area;
        this.crew = crew;
    }

    public Optional<Point> nextLocationToExploit()
    {
        Optional<PrimaryContract> selectedContract = selectContract();

        if (!selectedContract.isPresent())
            return Optional.empty();

        Optional<Point> closestBiomePoint = findClosestBiomePoint(selectedContract.get());
        Optional<Point> closestResourcePoint = findClosestResourcePoint(selectedContract.get());

        return Stream.of(closestBiomePoint, closestResourcePoint)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator.comparingDouble(point -> point.distance(crew.getLocation())))
                .findFirst();
    }

    private Optional<Point> findClosestResourcePoint(PrimaryContract primaryContract)
    {
        return map.getPoints().stream()
                .filter(point -> !simulatedMap.getTile(point).isAlready(GroundActionTile.VISITED))
                .filter(point -> simulatedMap.getTile(point)
                        .getResourceInformationList().stream()
                        .anyMatch(resourceInformation -> resourceInformation.getResource() == primaryContract.getResource()))
                .sorted(Comparator.comparingDouble(point -> point.distance(crew.getLocation())))
                .findFirst();
    }

    private Optional<Point> findClosestBiomePoint(PrimaryContract primaryContract)
    {
        Optional<Cluster> clusterOptional = getClusterToExploit(primaryContract);

        if (!clusterOptional.isPresent())
            return Optional.empty();

        return clusterOptional.get().stream()
                .filter(point -> !map.getTile(point).isAlready(GroundActionTile.VISITED))
                .sorted(Comparator.comparingDouble(crew.getLocation()::distance))
                .findFirst();
    }

    private Optional<Cluster> getClusterToExploit(PrimaryContract primaryContract)
    {
        return Arrays.stream(Biome.values())
                .filter(biome -> isBiomeUsefulForPrimaryContract(biome, primaryContract))
                .map(area::getCluster)
                .sorted(Comparator.comparingDouble(cluster -> compareClusterProductivity(cluster, primaryContract)))
                .findFirst();
    }

    private double compareClusterProductivity(Cluster cluster, PrimaryContract primaryContract)
    {
        return 1d / cluster.size() / cluster.getBiome().getResourceMap().get(primaryContract.getResource());
    }

    private Optional<PrimaryContract> selectContract()
    {
        Optional<PrimaryContract> primaryContract = getResourceToExploit();
        Optional<ManufacturedContract> manufacturedContract = getManufacturedToCraft();
        Function<ManufacturedContract, Optional<PrimaryContract>> findAPrimaryContractForTransform = m -> m.getPrimaryContracts().stream().filter(ResourceContract::notComplete).findFirst();
        if (primaryContract.isPresent() && manufacturedContract.isPresent())
        {
            if (getDifficulty(primaryContract.get()) <= getDifficulty(manufacturedContract.get()))
                return primaryContract;
            else
                return findAPrimaryContractForTransform.apply(manufacturedContract.get());
        }
        return manufacturedContract.map(findAPrimaryContractForTransform).orElse(primaryContract);
    }

    private Optional<PrimaryContract> getResourceToExploit()
    {
        return contract.getPrimaryContracts().stream().filter(ResourceContract::notComplete).sorted(Comparator.comparingDouble(this::getDifficulty)).findFirst();
    }

    private Optional<ManufacturedContract> getManufacturedToCraft()
    {
        return contract.getManufacturedContracts().stream().filter(ResourceContract::notComplete).sorted(Comparator.comparingDouble(this::getDifficulty)).findFirst();
    }

    private double getDifficulty(ManufacturedContract manufacturedContract)
    {
        return manufacturedContract.getPrimaryContracts().stream().mapToDouble(this::getDifficulty).sum() + craftDifficulty(manufacturedContract);
    }

    private double craftDifficulty(ManufacturedContract manufacturedContract)
    {
        return manufacturedContract.getRemainingQuantity() * manufacturedContract.getResource().getFactor();
    }

    private double getDifficulty(PrimaryContract primaryContract)
    {
        double difficulty = Double.POSITIVE_INFINITY;
        double biomeDifficulty;

        for (Biome biome : Biome.values())
        {
            if (isBiomeUsefulForPrimaryContract(biome, primaryContract))
            {
                biomeDifficulty = evaluateBiomeDifficulty(biome, primaryContract);
                if (biomeDifficulty < difficulty)
                    difficulty = biomeDifficulty;
            }
        }
        return difficulty;
    }

    private boolean isBiomeUsefulForPrimaryContract(Biome biome, PrimaryContract primaryContract)
    {
        return biome.getAssociateResources().contains(primaryContract.getResource()) && area.getArea(biome) > 0;
    }

    private double evaluateBiomeDifficulty(Biome biome, PrimaryContract primaryContract)
    {
        return evaluateContractDifficulty(primaryContract) / biome.getResourceMap().get(primaryContract.getResource()) / area.getArea(biome);
    }

    private double evaluateContractDifficulty(PrimaryContract primaryContract)
    {
        return 100d / primaryContract.getTotalQuantity() + primaryContract.getRemainingQuantity() / 100;
    }

}
