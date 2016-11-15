package fr.unice.polytech.si3.qgl.iaad.Actions;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Fly extends Area{

    public Fly() {
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.FLY.getName()).toString();
    }
}
