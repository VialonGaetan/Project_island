package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;

/**
 * On Scan pour trouver le site d'urgence et les criques présentent sur l'île.
 *
 * @author Alexandre Clement
 * @since 15/01/2017.
 */
class ScanIsland extends Oriented implements Protocol
{
    /**
     * @param direction l'orientation du drone
     * @param sense     le sens dans lequel on parcours l'île.
     *                  On balaye l'île de bout en bout en suivant un sens de parcours,
     *                  puis, lorsque le drone atteint la limite de l'île,
     *                  le drone fait demi-tour en inversant le sens de parcours
     *                  Après un aller-retour, l'île a été entièrement parcourue par le drone
     */
    ScanIsland(IslandMap map, Direction direction, Direction sense)
    {
        super(map, direction, sense);
    }

    @Override
    public Decision nextAction()
    {
        return new Scan();
    }

    /**
     * On ajoute les éléments trouver à la board
     *
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return Land si on trouve une crique
     * FlyOnIsland si on est toujours sur l'île
     * ReturnToIsland sinon pour revenir sur l'île
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        // Si on est déjà passer sur cette tuile
        if (getMap().getBiomes(getMap().getLocation()).length > 1)
            return new StopAerial();
        // Sinon, on ajoute les biomes
        for (int i = 0; i < result.nbBiomes(); i++)
            getMap().addBiomes(Biome.valueOf(result.getBiome(i)));
        // On ajoute les criques
        for (int i = 0; i < result.nbCreeks(); i++)
            getMap().addCreeks(result.getCreeks(i));
        // On ajout le site d'urgence
        if (result.getSites() != null)
            getMap().addEmergencySite(result.getSites());

        // Si on est en limite de carte, on arrête la partie
        if (getMap().getNumberOfAvailablePoints(getHeading()) < 1 && getMap().isDirectionFinished(getHeading()))
            return new StopAerial();

        // Si la tuile contient un autre biome que le biome OCEAN, on continue de parcourir l'île
        if (result.nbBiomes() > 1 || Biome.valueOf(result.getBiome(0)) != Biome.OCEAN)
            return new FlyOnIsland(getMap(), getHeading(), getSense());
        // Sinon, la tuile ne contient que le biome OCEAN
        // Alors on est en dehors de l'île
        // On lance le protocole ReturnToIsland pour y revenir
        return new ReturnToIsland(getMap(), getHeading(), getSense());
    }
}
