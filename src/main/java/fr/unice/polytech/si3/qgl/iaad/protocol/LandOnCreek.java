package fr.unice.polytech.si3.qgl.iaad.protocol;

import fr.unice.polytech.si3.qgl.iaad.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.player.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;

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
