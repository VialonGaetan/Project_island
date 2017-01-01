package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.awt.*;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 *         <p>
 *         Accoste sur l'île
 */
class LandOnIsland implements Protocol
{
    /**
     * La carte utilisée
     */
    private IslandMap map;
    /**
     * La position de la crique sur laquelle on veut accoster
     */
    private Point position;

    /**
     * @param map      la carte utilisée
     * @param position la position de la crique sur laquelle on veut accoster
     */
    LandOnIsland(IslandMap map, Point position)
    {
        this.map = map;
        this.position = position;
    }

    /**
     * @return Land
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        Explorer.areaPhase = false;
        return new Land(map.getCreekIds(position)[0], 1);
    }

    /**
     * todo: Ajouter l'initialisation de la phase terrestre
     * On lance la phase terrestre
     *
     * @param result le résultat de l'action effectué
     * @return l'initialisation de la phase terrestre
     */
    @Override
    public Protocol setResult(Area result)
    {
        // Passage en phase terrestre
        return new StopAerial();
    }
}
