package fr.unice.polytech.si3.qgl.iaad.util.resource;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.SecondContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Théo on 21/02/2017.
 */
public class Craft implements fr.unice.polytech.si3.qgl.iaad.future.Craft{

    Map<Resource, Integer> reagent;
    Map<Resource, Integer> products;
    SecondContract secondContract;

    public Craft(Context cx){
        this.reagent = new HashMap<>();
        this.products =new HashMap<>();
        secondContract = new SecondContract(cx);
    }

    @Override
    public Map<Resource, Integer> getReagent(Resource resource) {
        return resource.getRecipe(resource);
    }

    @Override
    public Map<Resource, Integer> getProducts(Resource resource) {
        return null;
    }


}
