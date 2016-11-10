package fr.unice.polytech.qgl.iaad;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Gaetan VIALON on 10/11/2016.
 */
public class Context {

    private JSONObject Initialise = new JSONObject();
    private JSONArray Contrats = new JSONArray();

    public Context(JSONObject Contract){

        this.Initialise = Contract;
        Contrats = Initialise.getJSONArray("contracts");
    }

    public String GetHeading(){
        return Initialise.getString(ArgInit.heading.getName());
    }

    public int GetMens(){
        return Initialise.getInt(ArgInit.men.getName());
    }

    public int GetBudget(){
        String ArgBudget = "budget";
        return Initialise.getInt(ArgInit.budget.getName());
    }

    public int NbofContrats(){
        return Contrats.length();
    }

    public int ContratAmmount(int n){
        return Contrats.getJSONObject(n).getInt(ArgInit.amount.getName());
    }

    public String ContratRessource (int n){
        return Contrats.getJSONObject(n).getString(ArgInit.resource.getName());
    }
}

enum ArgInit {
    heading("heading"),
    men("men"),
    budget("budget"),
    amount("amount"),
    resource("resource");

    private String name;

    public String getName() {
        return name;
    }

    ArgInit(String name) {
        this.name = name;
    }
}