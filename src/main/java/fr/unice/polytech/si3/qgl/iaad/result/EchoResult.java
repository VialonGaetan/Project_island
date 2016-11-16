package fr.unice.polytech.si3.qgl.iaad.result;

import fr.unice.polytech.si3.qgl.iaad.Actions.Echo;

/**
 * Created by user on 15/11/2016.
 */
public class EchoResult extends AreaResult{

    public EchoResult(String result) {

    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public String getStatus() {
        return null;
    }
}
