package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class SearchCreek implements Protocol
{
    private Direction direction;
    private int step;
    private Direction scan;

    SearchCreek(Direction direction)
    {
        this(direction, direction.getRight());
    }

    public SearchCreek(Direction direction, Direction scan)
    {
        this.direction = direction;
        this.scan = scan;
        step = 0;
    }

    @Override
    public Action nextAction()
    {
        step += 1;
        if ((step & 1) != 0)
            return new Scan();
        else
            return new Fly();
    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        if ((step & 1) == 0)
            return this;
        if (result.nbCreeks() > 0)
            return new Land(result.getCreeks(0));
        if (result.nbBiomes() != 1 || Element.valueOf(result.getBiome(0)) != Element.OCEAN)
            return this;
        if (step == 0)

        step = 0;
        direction = direction.getBack();
        return new HalfTurn(this, direction, scan);
    }
}
