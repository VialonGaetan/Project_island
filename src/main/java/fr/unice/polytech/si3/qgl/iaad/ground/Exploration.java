package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.StopGround;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.util.HashMap;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class Exploration {
    /**
     * Palier de budget faible
     */
    private static final int LOW_BUDGET = 50;
    /**
     * Budget disponible
     */
    private int budget;

    public static int lasti=0;

    /**
     * Contrats disponibles
     */
    private Context context ;

    private Direction direction;

    /**
     * Le protocole actuellement utilisé par le drone
     */
    private ProtocolGround protocol;

    private HashMap<Resource,Integer> contrat = new HashMap<>();

    /**
     * Initialise le drone
     *
     * @param budget  budget available
     * @param context context of the game
     * @param map     current map
     */
    public Exploration(int budget, IslandMap map, Context context)
    {
        this.budget = budget;
        this.context=context;
        for (int i = 0; i <context.numberOfContrats() ; i++) {
            contrat.put(Resource.valueOf(context.getContract(i).getResource()),context.getContract(i).getAmount());
        }
        protocol = new InitialisationGround();
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
            // sinon, on a plus assez de budget pour continuer
        }
        catch (InvalidMapException exception)
        {
            // on rencontre un problème avec la carte
        }
        // le drone a rencontré un problème: la partie s'arrête
        protocol = new StopExplorer();
        return new StopGround();
    }

    /**
     * Renouvelle le protocole en fonction du résultat de l'action précédente
     *
     * @param results le résultat de l'action précédente
     */
    public void getResult(Ground results) throws InvalidMapException
    {
        Ground groundResult = (Ground) results;
        if (budget < LOW_BUDGET)
            return;
        budget -= groundResult.getCost();
        protocol = protocol.setResult(groundResult);
    }
}
