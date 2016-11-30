package fr.unice.polytech.si3.qgl.iaad.init;

import fr.unice.polytech.si3.qgl.iaad.Execption.InvalidIndexExecption;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;


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

        for(int i=0; i<jsonObject.getJSONArray(ArgContext.contracts.getName()).length(); i++)
        {
            Contract contract=new Contract(jsonObject.getJSONArray(ArgContext.contracts.getName()).getJSONObject(i).getInt(ArgContext.amount.getName()),jsonObject.getJSONArray(ArgContext.contracts.getName()).getJSONObject(i).getString(ArgContext.resource.getName()));
            contracts.add(contract);
        }

    }

    /**
     * Recupere la direction dans laquelle se trouve le drone au depart
     * @return direction de depart du drone(String)
     */
    public String getHeading(){ return heading; }

    /**
     * Recupere le nombre d'hommes disponible ecrit sur le contrat
     * @return le nombre d'hommes(entier)
     */
    public int getMen(){ return numberOfMen; }

    /**
     * Recupere le budget disponible ecrit sur le contrat
     * @return budget total (entier)
     */
    public int getBudget(){ return budget; }

    /**
     * Recupere le nombre de contrat du client
     * @return nombre de contrat (entier)
     */
    public int numberOfContrats(){ return contracts.size(); }

    /**
     *
     * @param index
     * @return
     * @throws InvalidIndexExecption
     */
    public Contract getContract(int index) throws InvalidIndexExecption {
        try{
            return contracts.get(index);
        }
        catch (Exception e){
            throw new InvalidIndexExecption(index, Context.class.getSimpleName());
        }
    }
}