package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class FindIsland implements Protocol
{
    private static final int CYCLE = 3;
    private Direction direction;
    private int step;
    private int[] out = new int[4];

    FindIsland(int[] out, Direction direction)
    {
        this.direction = direction;
        this.out = out;
        step = 0;
    }

    @Override
    public Action nextAction()
    {
        step += 1;
        switch (step % CYCLE)
        {
            case 2:
                if (out[direction.getRight().ordinal()] > 0)
                    return new Echo(direction.getRight());
                step += 1;
            case 0:
                if (out[direction.getLeft().ordinal()] > 0)
                    return new Echo(direction.getLeft());
                step += 1;
            case 1:
                if (out[direction.ordinal()] > step / CYCLE + 1)
                    return new Fly();
            default:
                return new Stop();
        }
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        switch (step % CYCLE)
        {
            case 2:
                if (Element.valueOf(result.getFound()) == Element.GROUND)
                    return new FlyToIsland(direction, direction.getRight(), result.getRange());
            case 0:
                if (Element.valueOf(result.getFound()) == Element.GROUND)
                    return new FlyToIsland(direction, direction.getLeft(), result.getRange());
        }
        return this;
    }
}
