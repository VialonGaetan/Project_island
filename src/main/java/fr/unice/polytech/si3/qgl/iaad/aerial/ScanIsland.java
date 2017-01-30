package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
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
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;

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
        protocol = new ScanToFindCreekAndSite(map, direction, sense);
    }

    @Override
    public Action nextAction() throws InvalidMapException
    {
        return protocol.nextAction();
    }

    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        return protocol.setResult(result);
    }

}
