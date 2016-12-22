package fr.unice.polytech.si3.qgl.iaad.actions;

import org.json.JSONObject;

/**
 * @author Gaetan Vialon
 *         Created the 15/11/2016.
 */
public class Explore extends Ground {

    public Explore() {
    }

    /**
     * Créé un objet JSON avec l'action ECHO
     *
     * @return String of JSON
     */
    @Override
    public String toJSON() {
        return new JSONObject().put("action", ArgActions.EXPLORE.getName()).toString();
    }

    @Override
    public Ground putResults(String result) {
        this.result = result;
        return this;
    }

    @Override
    public int nbResourceExplore(){
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).length();
    }

    @Override
    public String getAmountExplore(int n) {
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getJSONObject(n).getString(ArgResult.AMOUNT.getName());
    }

    @Override
    public String getRessourceExplore(int n) {
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getJSONObject(n).getString(ArgResult.AMOUNT.getName());
    }

    @Override
    public String getCondExplore(int n) {
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.RESOURCES.getName()).getJSONObject(n).getString(ArgResult.AMOUNT.getName());
    }

    @Override
    public String getPoisExplore() {
        return new JSONObject(result).getJSONObject(ArgResult.EXTRAS.getName()).getJSONArray(ArgResult.POIS.getName()).getString(0);
    }
}
