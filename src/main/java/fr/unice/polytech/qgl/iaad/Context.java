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
        String Arghead = "heading";
        return Initialise.getString(Arghead);
    }

    public int GetMens(){
        String ArgMen = "men";
        return Initialise.getInt(ArgMen);
    }

    public int GetBudget(){
        String ArgBudget = "budget";
        return Initialise.getInt(ArgBudget);
    }

    public int NbofContrats(){
        return Contrats.length();
    }
    public int ContratAmmount(int n){
        String ArgAmmount ="amount";
        return Contrats.getJSONObject(n).getInt(ArgAmmount);
    }

    public String ContratRessource (int n){
        String ArgRessource = "resource";
        return Contrats.getJSONObject(n).getString(ArgRessource);
    }


}
