package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

/**
 * Le drone doit trouver la crique la plus proche du site d'urgence.
 *
 * @author Alexandre Clement
 */
public class Drone
{
    /**
     * Palier de budget faible
     */
    static final int LOW_BUDGET = 50;
    /**
     * Définit si on cherche le site d'urgence
     */
    static boolean searchEmergencySite = false;
    /**
     * Budget disponible
     */
    private int budget;

    /**
     * Le protocole actuellement utilisé par le drone
     */
    private Protocol protocol;

    /**
     * Initialise le drone
     *
     * @param budget  budget available
     * @param heading heading of the drone
     * @param map     current islandMap
     */
    public Drone(int budget, Direction heading, IslandMap map)
    {
        this.budget = budget;
        map.setOutOfRange(heading.getBack(), 0);
        protocol = new Initialisation(map, heading);
    }

    /**
     * Le drone fait une action
     *
     * @return la prochaine action du drone
     */
    public Action doAction()
    {
        Action action;
        try
        {
            action = protocol.nextAction();
            if (budget > LOW_BUDGET)
                return action;
            // On a plus assez de budget
            protocol = new StopAerial();
            return new Stop();
        }
        catch (InvalidMapException exception)
        {
            // Une erreur s'est produite
            protocol = new StopAerial();
            return new Stop();
        }
    }

    /**
     * Renouvelle le protocole en fonction du résultat de l'action précédente
     *
     * @param results le résultat de l'action précédente
     */
    public void getResult(Area results) throws InvalidMapException
    {
        if (budget < LOW_BUDGET)
            return;
        budget -= results.getCost();
        protocol = protocol.setResult(results);
    }

    public int getBudget()
    {
        return budget;
    }
}
