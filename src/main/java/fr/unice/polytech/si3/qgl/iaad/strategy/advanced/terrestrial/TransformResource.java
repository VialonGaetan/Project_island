package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Transform;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.TransformResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public class TransformResource implements Protocol
{
    private final Context context;
    private final IslandMap map;
    private final Crew crew;
    private final ManufacturedContract manufacturedContract;

    public TransformResource(Context context, IslandMap map, Crew crew, ManufacturedContract manufacturedContract)
    {
        this.context = context;
        this.map = map;
        this.crew = crew;
        this.manufacturedContract = manufacturedContract;
    }

    @Override
    public Decision takeDecision()
    {
        crew.craft(manufacturedContract.getResource(), manufacturedContract.getRemainingQuantity());
        return new Transform(manufacturedContract.getResource(), manufacturedContract.getRemainingQuantity());
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        TransformResult transformResult = new TransformResult(result);
        manufacturedContract.collect(transformResult.getTransformProduction());
        return new ScheduleCrewPath(context, map, crew);
    }
}
