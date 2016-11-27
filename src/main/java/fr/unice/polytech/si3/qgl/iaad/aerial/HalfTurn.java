package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Heading;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class HalfTurn implements Protocol
{

    private Protocol protocol;
    private Direction target;
    private Direction direction;
    private int step;

    HalfTurn(Protocol protocol, Direction target, Direction direction)
    {
        this.protocol = protocol;
        this.target = target;
        this.direction = direction;
        step = 0;
    }

    @Override
    public Action nextAction() {
        step += 1;
        if (step == 1)
            return new Heading(direction);
        return new Heading(target);

    }

    @Override
    public Protocol setResult(AreaResult result) {
        if (step == 2)
            return protocol;
        return this;
    }
}
