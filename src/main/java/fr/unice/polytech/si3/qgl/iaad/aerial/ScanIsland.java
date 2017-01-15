package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Parcours l'île pour trouver une crique.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
 */
class ScanIsland implements Protocol
{
    /**
     * La carte utilisée
     */
    private IslandMap map;
    /**
     * L'orientation du drone
     */
    private Direction direction;
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;
    /**
     * Le sens de parcours de l'île
     * <p>
     * On balaye l'île de bout en bout en suivant un sens de parcours,
     * puis, lorsque le drone atteint la limite de l'île,
     * le drone fait demi-tour en inversant le sens de parcours
     * Après un aller-retour, l'île a été entièrement parcourue par le drone
     */
    private Direction sense;

    /**
     * @param direction l'orientation du drone
     * @param sense     le sens dans lequel on parcours l'île
     */
    ScanIsland(IslandMap map, Direction direction, Direction sense)
    {
        this.map = map;
        this.direction = direction;
        this.sense = sense;
        protocol = new ScanToFindCreek();
    }

    @Override
    public Action nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        protocol = protocol.setResult(result);
        return protocol;
    }

    /**
     * On Fly au dessus de l'île
     */
    private class FlyOnIsland implements Protocol
    {
        @Override
        public Action nextAction() throws InvalidMapException
        {
            map.moveLocation(direction);
            return new Fly();
        }

        @Override
        public Protocol setResult(Area result)
        {
            return new ScanToFindCreek();
        }
    }

    /**
     * On Scan pour trouver le site d'urgence et les criques présentent sur l'île
     */
    private class ScanToFindCreek implements Protocol
    {
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
                return new FlyOnIsland();
            // Sinon, la tuile ne contient que le biome OCEAN
            // Alors on est en dehors de l'île
            // On lance le protocole ReturnToIsland pour y revenir
            return new ReturnToIsland(map, direction, sense);
        }
    }
}
