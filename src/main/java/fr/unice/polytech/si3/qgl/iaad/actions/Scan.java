package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * Created by user on 15/11/2016.
 */
public class Scan extends Area {


    public Scan() {
    }

    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.SCAN.getName()).toString();
    }
}