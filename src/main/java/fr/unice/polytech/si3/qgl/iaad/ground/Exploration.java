package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;
import fr.unice.polytech.si3.qgl.iaad.actions.StopGround;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 *         Inspiré de la strategie aerienne d'Alexandre
 */
public class Exploration {

    /**
     * Palier de budget minium
     * si on arrive à ce pallier nous arretons la partie pour ne pas crash
     */
    private static final int LOW_BUDGET = 200;

    /**
     * Budget disponible
     */
    private int budget;

    /**
     * Pour l'instant c'est pas très propre mais ça permet de ne pas tourner en rond en attend la islandMap
     */
    public static int lasti = 0;

    /**
     * Contrats disponibles
     */
    private Context context;

    /**
     * Protocole utilisé par les marins
     */
    private ProtocolGround protocol;

    /**
     * Hashmap avec tous les contrats
     * en clé nous avons les resources et en value le nombre de resource necessaire
     */
    private Map<Resource, Integer> contrat = new HashMap<Resource, Integer>();

    public Exploration(int budget, IslandMap map, Context context) {
        this.budget = budget;
        this.context = context;
        for (int i = 0; i < context.numberOfContrats(); i++) {
            contrat.put(Resource.valueOf(context.getContract(i).getResource()), context.getContract(i).getAmount());
        }
        protocol = new InitialisationGround(contrat, Direction.N);
    }

    /**
     * Prise de decision de la strategie
     * @return next Action
     */

    public Action doAction() {
        Action action;
        action = protocol.nextAction();
        if (budget > LOW_BUDGET)
            return action;
        else{
            protocol = new StopExplorer();
            return new StopGround();
        }
    }

    /**
     * Renouvelle le protocole en fonction du résultat de l'action précédente
     *
     * @param results le résultat de l'action précédente
     */

    public void getResult(Ground results){
        Ground groundResult = (Ground) results;
        if (budget < LOW_BUDGET)
            return;
        budget -= groundResult.getCost();
        protocol = protocol.setResult(groundResult);
    }
}
