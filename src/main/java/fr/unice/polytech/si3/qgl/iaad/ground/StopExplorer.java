package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.actions.StopGround;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.awt.*;


/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class StopExplorer implements ProtocolGround{


    StopExplorer()
    {
    }
    /**
     * @return Stop
     */
    @Override
    public Action nextAction() throws InvalidMapException
    {
        return new StopGround();
    }

    /**
     * @param result le résultat de l'action effectué
     * @return null
     */
    @Override
    public ProtocolGround setResult(Ground result) throws InvalidMapException
    {
        return new StopExplorer();
    }
}