package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.StopGround;


/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class StopExplorer implements ProtocolGround{

    /**
     * @return Stop
     */
    @Override
    public Decision nextAction()
    {
        return new StopGround();
    }

    /**
     * @param result le résultat de l'fr.unice.polytech.si3.qgl.iaad.action effectué
     * @return null
     */
    @Override
    public ProtocolGround setResult(Ground result)
    {
        return null;
    }
}
