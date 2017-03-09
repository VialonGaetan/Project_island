package fr.unice.polytech.si3.qgl.iaad.util.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 14/11/2016.
 * @author Theo CHOLLEY
 */

public enum Resource {
    FISH("FISH", true, null, 0, null, 0),
    FLOWER("FLOWER", true, null, 0, null, 0),
    FRUITS("FRUITS", true, null, 0, null, 0),
    FUR("FUR", true, null, 0, null, 0),
    ORE("ORE", true, null, 0, null, 0),
    QUARTZ("QUARTZ", true, null, 0, null, 0),
    SUGAR_CANE("SUGAR_CANE", true, null, 0, null, 0),
    WOOD("WOOD", true, null, 0, null, 0),
    GLASS("GLASS", false, Resource.QUARTZ, 10, Resource.WOOD,5 ),
    INGOT("INGOT", false, Resource.ORE, 5, Resource.WOOD,5),
    LEATHER("LEATHER", false, Resource.FUR, 3, null, 0),
    PLANK("PLANK", false, Resource.WOOD, 1, null, 0),
    RUM("RUM", false, Resource.SUGAR_CANE, 10, Resource.FRUITS,1);

    private String name;
    private Resource  reagent1, reagent2;
    private int qte1, qte2;
    private Boolean isPrimary;

    public String getName() {
        return name;
    }

    public Boolean isPrimary(){
        return isPrimary;
    }

    Resource(String name, Boolean isPrimary, Resource reagent1, Integer qte1, Resource reagent2, Integer qte2) {
        this.name = name;
        this.isPrimary = isPrimary;
        this.reagent1=reagent1;
        this.reagent2=reagent2;
        this.qte1=qte1;
        this.qte2=qte2;
    }

    public Resource getReagent1() {
        return reagent1;
    }

    public Resource getReagent2() {
        return reagent2;
    }

    public int getQte1() {
        return qte1;
    }

    public int getQte2() {
        return qte2;
    }

    public Map getRecipe(Resource resource){
        if (resource.isPrimary()) return null;
        Map<Resource, Integer> recipe = new HashMap<>();
        if (resource.getQte1()!=0) recipe.put(resource.getReagent1(), resource.getQte1());
        if (resource.getQte2()!=0) recipe.put(resource.getReagent2(),resource.getQte2());
        return recipe;
    }

}
