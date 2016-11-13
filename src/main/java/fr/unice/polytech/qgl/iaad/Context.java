package fr.unice.polytech.qgl.iaad;

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
public class Context {

    private int numberOfMen;
    private int budget;
    private List<Contract> contracts;
    private String heading;

    /**
     * convert JSONObject in each attributes
     */

    public Context(JSONObject jsonObject){
        numberOfMen=jsonObject.getInt(Argument.men.getName());
        budget=jsonObject.getInt(Argument.budget.getName());
        heading=jsonObject.getString(Argument.heading.getName());

        contracts=new ArrayList<>();

        for(int i=0; i<jsonObject.getJSONArray("contracts").length(); i++)
        {
            Contract contract=new Contract(jsonObject.getJSONArray("contracts").getJSONObject(i).getInt(Argument.amount.getName()),jsonObject.getJSONArray("contracts").getJSONObject(i).getString(Argument.resource.getName()));
            contracts.add(contract);
        }

    }

    public String getHeading(){ return heading; }

    public int getMens(){ return numberOfMen; }

    public int getBudget(){ return budget; }

    public int numberOfContrats(){ return contracts.size(); }

    public Contract getContract(int index) {
        if(index<0 || index >=numberOfContrats()) System.err.println("Contract out of range");
        return contracts.get(index);
    }
}

enum Argument {
    heading("heading"),
    men("men"),
    budget("budget"),
    amount("amount"),
    resource("resource");

    private String name;

    public String getName() { return name; }

    Argument(String name) { this.name = name; }
}