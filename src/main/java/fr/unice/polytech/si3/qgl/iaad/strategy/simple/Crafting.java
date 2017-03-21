package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Transform;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.TransformResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.strategy.advanced.terrestrial.ScheduleCrewPath;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.SecondContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Crew;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Théo on 16/03/2017.
 */

public class Crafting implements Protocol {

    private Transform action;
    private TransformResult result;
    private SecondContract secondContract;
    private Resource resource;
    private Basket basket;
    private Context context;
    private IslandMap map;
    private Crew crew;


    Crafting(Resource resource, SecondContract secondContract, Basket basket, Context context, Crew crew, IslandMap map){
        this.resource= resource;
        this.crew=crew;
        this.map=map;
        this.context=context;
        this.basket= basket;
        if (resource.getReagent1()!=null && resource.getReagent2()!=null)
            this.action=new Transform(resource.getReagent1(), resource.getQte1(), resource.getReagent2(), resource.getQte2());
        if (resource.getReagent1()!=null && resource.getReagent2()==null)
            this.action=new Transform(resource.getReagent1(), resource.getQte1());
        this.secondContract=secondContract;
    }


    @Override
    public Decision takeDecision() {
        updateBasket(resource);
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        this.result = new TransformResult(result);
        if (this.result.getTransformProduction()>0){
            if (basket.containsKey(resource))this.basket.put(resource, this.basket.get(resource)+this.result.getTransformProduction());
            else{
                this.basket.put(resource,this.result.getTransformProduction());
            }
        }
        else if (this.secondContract.isCraftable(this.resource, this.basket)){
            return new Crafting(resource,secondContract,basket, context,crew,map);
        }
        return new ScheduleCrewPath(context,map,crew); //A modifier en fonction de ce qu'on veut faire dans ce cas (le craft a échoué, et on a plus assez de ressources pour le reCraft)
    }

    public void updateBasket(Resource resource){
        if (resource.isPrimary())return;
        HashMap<Resource, Integer> recipe = (HashMap<Resource, Integer>) resource.getRecipe(resource);
        Set listKeys=recipe.keySet();
        Iterator iterateur=listKeys.iterator();
        Resource res = (Resource) iterateur.next();
        basket.put(res,basket.get(res)-recipe.get(res));
        res = (Resource) iterateur.next();
        if (res!=null) basket.put(res, basket.get(res)-recipe.get(res));
    }

}
