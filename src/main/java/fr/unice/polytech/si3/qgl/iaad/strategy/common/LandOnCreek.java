package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;

/**
 * @author Alexandre Clement
 * @since 23/02/2017.
 */
public class LandOnCreek implements Protocol
{

    private final Protocol exit;
    private final Creek creek;
    private final int people;

    public LandOnCreek(Protocol exit, Creek creek, int people)
    {
        this.exit = exit;
        this.creek = creek;
        this.people = people;
    }

    @Override
    public Decision takeDecision()
    {
        return new Land(creek.getId(), people);
    }

    @Override
    public Protocol acknowledgeResults(Result result)
    {
        return exit;
    }
}
