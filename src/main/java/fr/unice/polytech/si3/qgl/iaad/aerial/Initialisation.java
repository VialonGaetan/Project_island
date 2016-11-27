package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Echo;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class Initialisation implements Protocol
{
    private int step;
    private int[] out = new int[] {-1, -1, -1, -1};
    private int[] ground = new int[] {-1, -1, -1, -1};
    private Direction direction;

    Initialisation(Direction heading)
    {
        step = 0;
        this.direction = heading;
        out[direction.getBack().ordinal()] = 0;
    }
    @Override
    public Action nextAction()
    {
        step += 1;
        switch (step)
        {
            case 1:
                return new Echo(direction);
            case 2:
                return new Echo(direction.getRight());
            case 3:
                return new Echo(direction.getLeft());
            default:
                return new Stop();
        }
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        switch (step)
        {
            case 1:
                if (Element.valueOf(result.getFound()) == Element.GROUND)
                    ground[direction.ordinal()] = result.getRange();
                else
                    out[direction.ordinal()] = result.getRange();
                break;
            case 2:
                if (Element.valueOf(result.getFound()) == Element.GROUND)
                    ground[direction.getRight().ordinal()] = result.getRange();
                else
                    out[direction.getRight().ordinal()] = result.getRange();
                break;
            case 3:
                if (Element.valueOf(result.getFound()) == Element.GROUND)
                    ground[direction.getLeft().ordinal()] = result.getRange();
                else
                    out[direction.getLeft().ordinal()] = result.getRange();

                for (int i=0; i<ground.length; i++)
                    if (ground[i] >= 0)
                        return new FlyToIsland(direction, Direction.values()[i], ground[i]);
                return new FindIsland(out, direction);
        }
        return this;
    }
}
