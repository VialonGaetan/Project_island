package fr.unice.polytech.si3.qgl.iaad.engine.player.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 *         Created the 15/11/2016.
 */
public class Explore extends Ground {


    public Explore() {
        actionType = ArgActions.EXPLORE;
    }

    /**
     * Créé un objet JSON avec l'fr.unice.polytech.si3.qgl.iaad.action ECHO
     * @return String of JSON
     */
    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("action", ArgActions.EXPLORE.getName());
    }

    @Override
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }

    @Override
    public int nbResourceExplore() {
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).length();
    }

    @Override
    public String getAmountExplore(int n) {
        try {
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getJSONObject(n).getString(ArgResult.AMOUNT.getName());
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public String getRessourceExplore(int n) {
        try {
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getJSONObject(n).getString(ArgResult.RESOURCE.getName());
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public String getCondExplore(int n) {
        try {
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getJSONObject(n).getString(ArgResult.COND.getName());
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public String getPoisExplore() {
        try {
            return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.POIS.getName()).getString(0);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
