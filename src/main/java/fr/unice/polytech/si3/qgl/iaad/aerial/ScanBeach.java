package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Le drone longe les côtes de l'île pour trouver une crique.
 * Définition de côtes: Une tuile composée de au moins deux biomes dont un OCEAN.
 * <p>
 * Created the 16/12/2016.
 *
 * @author Alexandre Clement
 */
class ScanBeach implements Protocol
{
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private Protocol protocol;

    /**
     * @param map     la map actuelle
     * @param heading l'orientation du drone
     * @param sense   le sens de recherche
     */
    ScanBeach(IslandMap map, Direction heading, Direction sense)
    {
        protocol = new ScanToFindCreek(map, heading, sense);
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
