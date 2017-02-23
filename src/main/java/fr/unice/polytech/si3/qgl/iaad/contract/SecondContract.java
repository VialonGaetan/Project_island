package fr.unice.polytech.si3.qgl.iaad.contract;

import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.resource.Resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Théo on 22/02/2017.
 */
public class SecondContract {

    private Map<Resource, Integer> toBeCrafted;
    private Map<Resource,Integer> secondContract;
    private Map<Resource, Integer> initialContract;

    public SecondContract(Context cx){
        initialContract=new HashMap<>();
        toBeCrafted = new HashMap<>();
        secondContract = new HashMap<>();
        for (int i=0; i<cx.numberOfContrats(); i++){
            initialContract.put(Resource.valueOf(cx.getContract(i).getResource()), cx.getContract(i).getAmount());
        }
    }


    /**
     * Forme un second contract : avec toutes les ressources primaires requises afin de satisfaire le client (+ ressources primaires qu'il faut pour crafter les manufacturées !)
     * et forme egalement une map "toBeCrafted" qui permet de recenser toutes les ressources que l'on doit crafter
     */

    public void createSecondContract(){
        Set listKeys=initialContract.keySet();
        Iterator iterateur=listKeys.iterator();
        Resource reagent;
        while(iterateur.hasNext())
        {
            Resource res = (Resource) iterateur.next();
            if (!Resource.isPrimary(res)){
                toBeCrafted.put(res, initialContract.get(res));
                Set listReagents =Resource.getRecipe(res).keySet();
                Iterator iterator=listReagents.iterator();
                while(iterator.hasNext())
                {
                    reagent = (Resource) iterator.next();
                    for (int i=0; i<this.initialContract.get(res); i++){
                        if (this.secondContract.containsKey(reagent)) this.secondContract.put(reagent,(int) this.secondContract.get(reagent)+Resource.getRecipe(res).get(reagent));
                        else this.secondContract.put(reagent,Resource.getRecipe(res).get(reagent));
                    }

                }
            }
            if (Resource.isPrimary(res)){
                if (this.secondContract.containsKey(res)) secondContract.put(res, this.secondContract.get(res)+initialContract.get(res));
                else secondContract.put(res,initialContract.get(res));
            }

        }
    }

    public Map<Resource, Integer> getToBeCrafted() {
        return toBeCrafted;
    }

    public Map<Resource, Integer> getSecondContract() {
        return secondContract;
    }

    public Map<Resource, Integer> getInitialContract() {
        return initialContract;
    }
}
