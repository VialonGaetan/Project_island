package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Glimpse;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;

import java.util.HashMap;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class FindRessource implements ProtocolGround {
    /**
     * L'orientation du drone
     */
    private Direction heading;
    /**
     * Le sous-protocole en cours d'utilisation
     */
    private ProtocolGround protocol;
    private int range;

    private HashMap<Resource, Integer> contrat;


    public FindRessource(HashMap contrat, Direction heading) {
        this.contrat = contrat;
        protocol = new GlimpseToFindRessource(heading, 4, contrat);
    }

    public FindRessource(HashMap contrat, Direction headingn, int range) {
        this.contrat = contrat;
        this.range = range;
        protocol = new GlimpseToFindRessource(heading, range, contrat);
    }

    @Override
    public Action nextAction() {
        return protocol.nextAction();
    }

    @Override
    public ProtocolGround setResult(Ground result) {
        return protocol = protocol.setResult(result);
    }


    /**
     * Fait un Glimpse de chaque coté des marins pour trouver les ressources
     */
    private class GlimpseToFindRessource implements ProtocolGround {
        private Direction direction;
        private int range;
        private int distance;
        private HashMap<Resource, Integer> contrat;

        private GlimpseToFindRessource(Direction direction, int range, HashMap contrat) {
            this.direction = direction;
            this.range = range;
            this.contrat = contrat;
        }

        @Override
        public Action nextAction() {
            return new Glimpse(direction, range);
        }


        @Override
        public ProtocolGround setResult(Ground result) {
            for (int i = 0; i < contrat.size() ; i++) {
                if ((distance = result.getDistanceResource((Resource)contrat.keySet().toArray()[i])) > -1) {
                    Exploration.lasti = 0;
                    return new Move(distance, direction, contrat);
                }
            }
            if (Exploration.lasti < 4) {

                return new GlimpseToFindRessource(direction.getRight(), 4, contrat);
            }
            Exploration.lasti = 0;
           return new StopExplorer();
        }
    }
}
