package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class FlyToIsland implements Protocol
{
    private Direction heading;
    private Direction target;
    private Direction scan;
    private int range;

    FlyToIsland(Direction heading, Direction target, int range)
    {
        this.scan = heading;
        this.heading = heading;
        this.target = target;
        this.range = range;
    }

    @Override
    public Action nextAction()
    {
        range -= 1;
        if (heading == target)
            return new Fly();
        heading = target;
        return new Heading(target);
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        if (range < 0)
            return new SearchCreek(heading, scan);
        return this;
    }
}
