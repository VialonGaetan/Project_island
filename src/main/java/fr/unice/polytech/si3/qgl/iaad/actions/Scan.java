package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.result.*;
import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 * Created the 15/11/2016.
 */
public class Scan extends Area {


    public Scan() {}

    /**
     * Créé un objet JSON avec l'action SCAN
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action" , ArgActions.SCAN.getName()).toString();
    }

    /**
     * Créé un objet ScanResult
     * @param result de l'explorer
     * @return ScanResult
     */
    @Override
    public AreaResult getResults(String result) {
        return new ScanResult(result);
    }
}
