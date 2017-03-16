package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;

/**
 * @author Alexandre Clement
 * @since 02/03/2017.
 */
public class AdvancedStrategy implements Protocol
{
    private final Protocol initialisation;

    public AdvancedStrategy(Context context, IslandMap islandMap, Drone drone)
    {
        initialisation = new Initialisation(context, islandMap, drone);
    }

    @Override
    public Decision takeDecision()
    {
        return initialisation.takeDecision();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return initialisation.acknowledgeResults(result);
    }
}
