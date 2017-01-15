package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * On Scan pour trouver le site d'urgence et les criques présentent sur l'île.
 *
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
class ScanToFindCreekAndSite extends Oriented implements Protocol
{
    private IslandMap map;
    private Direction sense;

    ScanToFindCreekAndSite(IslandMap map, Direction direction, Direction sense)
    {
        super(direction);
        this.map = map;
        this.sense = sense;
    }
    @Override
    public Action nextAction()
    {
        return new Scan();
    }

    /**
     * On ajoute les éléments trouver à la map
     *
     * @param result le résultat de l'action effectué
     * @return Land si on trouve une crique
     * FlyOnIsland si on est toujours sur l'île
     * ReturnToIsland sinon pour revenir sur l'île
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        // Si on est déjà passer sur cette tuile
        if (map.getBiomes(map.getLocation()).length > 1)
            return new StopAerial();
        // Sinon, on ajoute les biomes
        for (int i = 0; i < result.nbBiomes(); i++)
            map.addBiomes(Element.valueOf(result.getBiome(i)));
        // On ajoute les criques
        for (int i = 0; i < result.nbCreeks(); i++)
            map.addCreeks(result.getCreeks(i));
        // On ajout le site d'urgence
        if (result.getSites() != null)
            map.addEmergencySite(result.getSites());

        // Si on est en limite de carte, on arrête la partie
        if (map.getNumberOfAvailablePoints(direction) < 1 && map.isDirectionFinished(direction))
            return new StopAerial();

        // Si la tuile contient un autre biome que le biome OCEAN, on continue de parcourir l'île
        if (result.nbBiomes() > 1 || Element.valueOf(result.getBiome(0)) != Element.OCEAN)
            return new FlyOnIsland(map, direction, sense);
        // Sinon, la tuile ne contient que le biome OCEAN
        // Alors on est en dehors de l'île
        // On lance le protocole ReturnToIsland pour y revenir
        return new ReturnToIsland(map, direction, sense);
    }
}
