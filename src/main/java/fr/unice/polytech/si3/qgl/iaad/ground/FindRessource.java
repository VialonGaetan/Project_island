package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Glimpse;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;

import java.util.HashMap;
import java.util.Map;

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

    private Map<Resource, Integer> contrat;


    public FindRessource(Map contrat, Direction heading) {
        this.contrat = contrat;
        this.heading=heading;
        protocol = new GlimpseToFindRessource(heading, 4, contrat);
    }

    public FindRessource(HashMap contrat, Direction heading, int range) {
        this.heading=heading;
        this.contrat = contrat;
        this.range = range;
        protocol = new GlimpseToFindRessource(heading, range, contrat);
    }

    @Override
    public Decision nextAction() {
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
        private Map<Resource, Integer> contrat;

        private GlimpseToFindRessource(Direction direction, int range, Map contrat) {
            this.direction = direction;
            this.range = range;
            this.contrat = contrat;
        }

        @Override
        public Decision nextAction() {
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
            else{
                Exploration.lasti = 0;
                return new Move(1,direction,contrat);
            }
        }
    }
}
