package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.Results;

/**
 * Created by user on 15/11/2016.
 */
public abstract class Area implements Action {

    public Direction direction;
    public AreaResult results;
    public String ID;
    public int people;
    public String rapport = "Nous allons être riche !!";

    public abstract AreaResult getResults(String result);

    public String getRapport()
    {
        return rapport;
    }
}
