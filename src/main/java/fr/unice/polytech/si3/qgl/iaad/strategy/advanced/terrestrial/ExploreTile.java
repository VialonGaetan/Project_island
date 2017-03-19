package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ExploreResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ExploreTile implements Protocol
{
    private final Context context;
    private final IslandMap map;
    private final Crew crew;
    private final Map<Resource, Double> prioritizedContract;

    ExploreTile(Context context, IslandMap map, Crew crew, Map<Resource, Double> prioritizedContract)
    {
        this.context = context;
        this.map = map;
        this.crew = crew;
        this.prioritizedContract = prioritizedContract;
    }

    @Override
    public Decision takeDecision()
    {
        return new Explore();
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        ExploreResult exploreResult = new ExploreResult(result);

        map.getTile(crew.getLocation()).setAsAlready(GroundActionTile.VISITED);

        for (ResourceInformation resourceInformation : exploreResult.getResourcesExplored())
        {
            if (isWorthToExploit(resourceInformation))
                return new ExploitTile(context, map, crew, resourceInformation.getResource());
        }
        return new ScheduleCrewPath(context, map, crew);
    }

    private boolean isWorthToExploit(ResourceInformation resourceInformation)
    {
        return prioritizedContract.containsKey(resourceInformation.getResource()) && resourceInformation.getResourceAmount().getQuantity() * resourceInformation.getResourceCondition().getCondition() * prioritizedContract.get(resourceInformation.getResource()) > 0;
    }
}
