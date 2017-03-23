package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Explore;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.ExploreResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.GroundActionTile;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
public class ExploreTile implements Protocol
{
    private final Context context;
    private final IslandMap map;
    private final Crew crew;

    public ExploreTile(Context context, IslandMap map, Crew crew)
    {
        this.context = context;
        this.map = map;
        this.crew = crew;
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

        acknowledgeMap(exploreResult);

        for (ResourceInformation resourceInformation : exploreResult.getResourcesExplored())
        {
            if (isWorthToExploit(resourceInformation))
                return new ExploitTile(context, map, crew, resourceInformation.getResource());
        }
        return new ScheduleCrewPath(context, map, crew);
    }

    private void acknowledgeMap(ExploreResult exploreResult)
    {
        map.getTile(crew.getLocation()).setAsAlready(GroundActionTile.VISITED);
        map.getTile(crew.getLocation()).addResourceInformationList(exploreResult.getResourcesExplored());
    }

    private boolean isWorthToExploit(ResourceInformation resourceInformation)
    {
        boolean isInContract = context.getContract().getTotalBasket().contains(resourceInformation.getResource());
        boolean isWorthToExploit = resourceInformation.getResourceCondition().getCondition() * resourceInformation.getResourceAmount().getQuantity() >= 0.5;
        return isInContract && isWorthToExploit;
    }
}
