package fr.unice.polytech.si3.qgl.iaad.util.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Théo on 21/02/2017.
 */
public class Craft implements fr.unice.polytech.si3.qgl.iaad.future.Craft{

    Map<Resource, Integer> reagent;
    Map<Resource, Integer> products;
    public Craft(){
        this.reagent = new HashMap<>();
        this.products =new HashMap<>();
    }

    public static void main(String[] args) {
        System.out.println("ok");
    }




    @Override
    public Map<Resource, Integer> getReagent(Resource resource) {
        return resource.getRecipe(resource);
    }


    @Override
    public Map<Resource, Integer> getProducts(Resource resource) {
        return null;
    }

    public void transformTheResource(Resource resource){
        if (resource.isPrimary()) return;
    }

}
