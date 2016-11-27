package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

/**
 * @author Alexandre Clement
 *         Created the 27/11/2016.
 */
public class Land implements Protocol
{

    private String creek;

    Land(String creek)
    {
        this.creek = creek;
    }

    @Override
    public Action nextAction() {
        return new Stop();
    }

    @Override
    public Protocol setResult(AreaResult result) {
        return this;
    }
}
