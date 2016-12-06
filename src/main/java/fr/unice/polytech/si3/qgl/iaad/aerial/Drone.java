
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 20/11/2016.
 *
 * Le drone doit trouver la crique la plus proche du site d'urgence
 */
public class Drone
{
    /**
     * Palier de budget faible
     */
    private static final int LOW_BUDGET = 200;

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
     * @param budget budget available
     * @param heading heading of the drone
     * @param map current map
     */
    public Drone(int budget, Direction heading, IslandMap map)
    {
        this.budget = budget;
        map.setOutOfRange(heading.getBack(), 0);
        protocol = new Initialisation(map, heading);
    }

    /**
     * Le drone fait une action
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
            // sinon, on a plus assez de budget pour continuer
        }
        catch (InvalidMapException exception)
        {
            // on rencontre un problème avec la carte
        }
        // le drone a rencontré un problème: la partie s'arrête
        protocol = new Land();
        return new Stop();
    }

    /**
     * Renouvelle le protocole en fonction du résultat de l'action précédente
     * @param results le résultat de l'action précédente
     */
    public void getResult(AreaResult results) throws InvalidMapException
    {
        if (budget < LOW_BUDGET)
            return;
        budget -= results.getCost();
        protocol = protocol.setResult(results);
    }
}
