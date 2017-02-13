package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.Move_to;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 05/02/2017.
 */
public class UTurn implements ProtocolGround {
    /**
     * Direction des actions des marins
     */
    private Direction turnsens, actual;
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private ProtocolGround protocol;

    private Map<Resource,Integer> contrat;


    /**
     *
     * @param actual direction actuel
     * @param turnsens sens de demi tour vers la droite ou gauche
     * @param contrat
     */
    public UTurn(Direction actual,Direction turnsens, Map contrat)
    {
        this.turnsens=turnsens;
        this.actual = actual;
        this.contrat=contrat;
    }

    @Override
    public Action nextAction()
    {
        return new Move_to(turnsens);
    }

    @Override
    public ProtocolGround setResult(Ground result)
    {
        if(actual.equals(turnsens)) {
            return new ExploreResource(actual.getBack(),contrat);
        }
        return new UTurn(actual,actual.getBack(),contrat);
    }
}
