package fr.unice.polytech.si3.qgl.iaad;

import fr.unice.polytech.si3.qgl.iaad.resource.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Théo on 21/02/2017.
 */
public class Craft {

    Map<Resource, Integer> reagent;
    Map<Resource, Integer> products;
    public Craft(){
        this.reagent = new HashMap<>();
        this.products =new HashMap<>();
    }

    public Boolean isPrimary(Resource resource){
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

    public String getRecipe(Resource resource){
        if (isPrimary(resource)) return null;
        switch (resource.getName()){
            case "GLASS" : {
                return "10 QUARTZ + 5 WOOD";
            }
            case "INGOT" : {
                return "5 ORE + 5 WOOD";
            }
            case "LEATHER" : {
                return "3 FUR";

            }
            case "PLANK" : {
                return "1 WOOD";

            }
            case "RUM" : {
                return "10 SUGAR_CANE + 1 FRUIT";
            }
            default: return null;
        }
    }




    public static void main(String[] args) {
        Craft craft = new Craft();
        System.out.println(craft.isPrimary(Resource.FISH));
        System.out.println(craft.isPrimary(Resource.GLASS));
        System.out.println(craft.getRecipe(Resource.FISH));
        System.out.println(craft.getRecipe(Resource.RUM));
    }


}
