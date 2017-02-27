package fr.unice.polytech.si3.qgl.iaad;

import fr.unice.polytech.si3.qgl.iaad.resource.Resource;

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

    public Boolean isPrimary(Resource resource) {
        switch (resource.getName()){
            case "FISH" : {
                return true;
            }
            case "FLOWER" : {
                return true;
            }
            case "FRUITS" : {
                return true;
            }
            case "FUR" : {
                return true;
            }
            case "ORE" : {
                return true;
            }
            case "QUARTZ" : {
                return true;
            }
            case "SUGAR_CANE" : {
                return true;
            }
            default : return false;
        }
    }


    @Override
    public Map<Resource, Integer> getReagent(Resource resource) {
        this.reagent=new HashMap<>();
        if (isPrimary(resource)) return null;
        switch (resource.getName()){
            case "GLASS" : {
                this.reagent.put(Resource.QUARTZ,10);
                this.reagent.put(Resource.WOOD,5);
                return this.reagent;
            }

            case "INGOT" : {
                this.reagent.put(resource.ORE,5);
                this.reagent.put(Resource.WOOD,5);
                return this.reagent;
            }

            case "LEATHER" : {
                this.reagent.put(Resource.FUR,3);
                return this.reagent;
            }

            case "PLANK" : {
                this.reagent.put(Resource.WOOD,1);
                return this.reagent;
            }

            case "RUM" : {
                this.reagent.put(Resource.SUGAR_CANE,10);
                this.reagent.put(Resource.FRUITS,1);
                return this.reagent;
            }
            default: return null;
        }
    }


    @Override
    public Map<Resource, Integer> getProducts(Resource resource) {
        return null;
    }

    public void transformTheResource(Resource resource){
        if (isPrimary(resource)) return;
    }

}
