package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.actions.ArgActions;
import org.json.JSONObject;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Decision
{
    ArgActions getActionEnum();

    JSONObject getJsonObject();
}
