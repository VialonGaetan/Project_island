package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Decision
{

    ArgActions getActionEnum();

    JSONObject getJsonObject();

    Decision putResults(String result);

    int getCost();

    String getStatus();
}
