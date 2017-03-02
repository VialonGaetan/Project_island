package fr.unice.polytech.si3.qgl.iaad.util.resource;

import java.util.HashMap;

/**
 * Created on 14/11/2016.
 * @author Gaetan VIALON
 */

public enum Resource {
    FISH("FISH"),
    FLOWER("FLOWER"),
    FRUITS("FRUITS"),
    FUR("FUR"),
    ORE("ORE"),
    QUARTZ("QUARTZ"),
    SUGAR_CANE("SUGAR_CANE"),
    WOOD("WOOD"),
    GLASS("GLASS"),
    INGOT("INGOT"),
    LEATHER("LEATHER"),
    PLANK("PLANK"),
    RUM("RUM");

    private String name;

    public String getName() {
        return name;
    }

    Resource(String name) {
        this.name = name;
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
            case "WOOD" : {
                return true;
            }
            default : return false;
        }
    }

    public static HashMap<Resource, Integer> getRecipe(Resource resource){
        HashMap<Resource, Integer> recipe = new HashMap<>();
        if (resource.isPrimary(resource)) return null;
        switch (resource.getName()){
            case "GLASS" : {
                recipe.put(Resource.QUARTZ,10);
                recipe.put(Resource.WOOD,5);
                return recipe;
            }
            case "INGOT" : {
                recipe.put(Resource.ORE, 5);
                recipe.put(Resource.WOOD, 5);
                return recipe;

            }
            case "LEATHER" : {
                recipe.put(Resource.FUR,3);
                return recipe;

            }
            case "PLANK" : {
                recipe.put(Resource.WOOD,1);
                return recipe;
            }
            case "RUM" : {
                recipe.put(Resource.SUGAR_CANE,10);
                recipe.put(Resource.FRUITS,1);
                return recipe;

            }
            default: return null;
        }
    }
}
