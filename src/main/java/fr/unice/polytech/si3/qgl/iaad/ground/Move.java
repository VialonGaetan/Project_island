package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.Move_to;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 15/01/2017.
 */
public class Move implements ProtocolGround{
    /**
     * Direction des actions des marins
     */
    private Direction direction;
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private ProtocolGround protocol;

    private Resource resource;

    private int nbofResource;

    private int distance;

    private Map<Resource,Integer> contrat;


    public Move(int distance, Direction direction, Map contrat)
    {
        this.direction=direction;
        this.distance = distance;
        this.contrat=contrat;
    }

    @Override
    public Action nextAction()
    {
        return new Move_to(direction);
    }

    @Override
    public ProtocolGround setResult(Ground result)
    {
        if(distance == 0) {
            Exploration.lasti = 0;
            return new InitialisationGround(contrat,direction);
        }
        else return new Move(distance-1,direction,contrat);
    }
}
