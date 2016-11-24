package fr.unice.polytech.si3.qgl.iaad.init;

import org.json.JSONObject;


import java.util.List;
import java.util.ArrayList;

/**
 * manage informations about :
 * men
 * budget
 * contracts
 * heading
 */
/**
 * Created on 10/11/2016.
 * @author Gaetan VIALON
 */

public class Context {

    private int numberOfMen;
    private int budget;
    private List<Contract> contracts;
    private String heading;

    /**
     * convert JSONObject in each attributes
     */

    public Context(JSONObject jsonObject){
        numberOfMen=jsonObject.getInt(ArgContext.men.getName());
        budget=jsonObject.getInt(ArgContext.budget.getName());
        heading=jsonObject.getString(ArgContext.heading.getName());

        contracts=new ArrayList<>();

        for(int i=0; i<jsonObject.getJSONArray("contracts").length(); i++)
        {
            Contract contract=new Contract(jsonObject.getJSONArray("contracts").getJSONObject(i).getInt(ArgContext.amount.getName()),jsonObject.getJSONArray("contracts").getJSONObject(i).getString(ArgContext.resource.getName()));
            contracts.add(contract);
        }

    }

    public String getHeading(){ return heading; }

    /**
     *
     * @return le nombre d'hommes(entier)
     */
    public int getMen(){ return numberOfMen; }

    public int getBudget(){ return budget; }

    public int numberOfContrats(){ return contracts.size(); }

    public Contract getContract(int index) {
        if(index<0 || index >=numberOfContrats()) System.err.println("Contract out of range");
        return contracts.get(index);
    }
}