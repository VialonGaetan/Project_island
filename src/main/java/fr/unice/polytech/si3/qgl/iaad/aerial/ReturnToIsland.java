package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class ReturnToIsland implements Protocol
{

    private Protocol protocol;
    private Direction direction;
    private Direction scan;
    private int step;

    public ReturnToIsland(Direction direction, Direction scan)
    {

        this.protocol = protocol;
        this.direction = direction;
        this.scan = scan;
        step = 0;
    }

    @Override
    public Action nextAction()
    {
        step =+ 1;
        switch (step)
        {
            case 1:
                return new Echo(direction);
            case 2:
                return new Heading(scan.getBack());
            case 3:
                return new Fly();
            case 4:
                return new Heading(direction);
        }
        return new Stop();

    }

    @Override
    public Protocol setResult(AreaResult result)
    {
        if (step == 1 && Element.valueOf(result.getFound()) == Element.GROUND)
            return new FlyToIsland(direction, direction, scan, result.getRange());
        if (step == 4)
            return new SearchCreek(direction, scan.getBack());
        return this;

    }
}
