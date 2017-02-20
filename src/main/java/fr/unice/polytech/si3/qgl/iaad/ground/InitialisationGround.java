package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 04/01/2017.
 */
public class InitialisationGround implements ProtocolGround{


    /**
     * Le sous-protocole en cours d'utilisation
     */
    private ProtocolGround protocol;

    private Map<Resource,Integer> contrat;

    private Direction direction;

    public InitialisationGround(Map contrat, Direction direction)
    {
        this.contrat=contrat;
        this.direction=direction;
        protocol = new ExploreToFindRessource(contrat,direction);
    }

    @Override
    public Decision nextAction()
    {
        return protocol.nextAction();
    }

    @Override
    public ProtocolGround setResult(Ground result)
    {
        return protocol = protocol.setResult(result);
    }


    private class ExploreToFindRessource implements ProtocolGround
    {
        private Direction direction;

        private Map<Resource,Integer>contrat;

        ExploreToFindRessource(Map contrat, Direction direction)
        {
            this.contrat=contrat;
            this.direction=direction;
        }

        @Override
        public Decision nextAction()
        {
            return new Explore();
        }

        @Override
        public ProtocolGround setResult(Ground result)
        {

            for (int i = 0; result.getRessourceExplore(i) != null; i++) {
                if (contrat.containsKey(Resource.valueOf(result.getRessourceExplore(i)))&& i>=Exploration.lasti)
                {
                    Exploration.lasti = i+1;
                    return new ExploitResource(Resource.valueOf(result.getRessourceExplore(i)),contrat.get(Resource.valueOf(result.getRessourceExplore(i))),contrat);
                }
            }
            Exploration.lasti = 0;
            return new FindRessource(contrat,direction);

        }
    }
}
