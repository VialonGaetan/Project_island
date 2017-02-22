package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.board.IslandMap;

import java.awt.*;

/**
 * Accoste sur l'île.
 * <p>
 * Created the 27/11/2016.
 *
 * @author Alexandre Clement
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
    public Decision nextAction() throws InvalidMapException
    {
        return new Land(map.getCreekIds(position)[0], 1);
    }

    /**
     * On lance la phase terrestre
     *
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return l'initialisation de la phase terrestre
     */
    @Override
    public Protocol setResult(Area result)
    {
        return new StopAerial();
    }
}
