package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.actions.ArgResult;
import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.format.json.JsonResult;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class EchoResultat {

    JsonResult result;

    EchoResultat(JsonResult result) {
        this.result = result;
    }

    public int getCots(){return result.getCost();}


    public int getRange(){
        return result.getRange();
    }

    public Element getFound(){
        return result.getFound();
    }
}
