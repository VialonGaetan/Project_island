package fr.unice.polytech.si3.qgl.iaad.ground;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.actions.Ground;

import java.util.Map;

/**
 * @author Gaetan Vialon
 *         Created the 05/02/2017.
 */
public class ExploreResource implements ProtocolGround{

    /**
     * Le sous-protocole en cours d'utilisation
     */
    private ProtocolGround protocol;

    private Direction direction;

    private Map<Resource,Integer> contrat;

    private boolean containFish = false;


    public ExploreResource(Direction direction, Map contrat)
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
        Exploration.lasti=0;
        if(containFish){
            return new FindRessource(contrat,direction);
        }
        return new StopExplorer();
    }
}
