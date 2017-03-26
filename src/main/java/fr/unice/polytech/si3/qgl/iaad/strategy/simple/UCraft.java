package fr.unice.polytech.si3.qgl.iaad.strategy.simple;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Transform;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.TransformResult;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.SecondContract;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

/**
 * Created by Théo on 16/03/2017.
 */
public class UCraft implements Protocol {

    private Transform action;
    private TransformResult result;
    private SecondContract secondContract;
    private Resource resource;

    UCraft(Resource resource, SecondContract secondContract){
        this.resource= resource;
        if (resource.getReagent1()!=null && resource.getReagent2()!=null)
            this.action=new Transform(resource.getReagent1(), resource.getQte1(), resource.getReagent2(), resource.getQte2());
        if (resource.getReagent1()!=null && resource.getReagent2()==null)
            this.action=new Transform(resource.getReagent1(), resource.getQte1());
        this.secondContract=secondContract;
    }


    @Override
    public Decision takeDecision() {
        this.secondContract.getSecondContract().put(resource,this.secondContract.getSecondContract().get(resource)-1);
        return action;
    }

    @Override
    public Protocol acknowledgeResults(Result result) {
        return new UCraft(resource,secondContract);
    }
}
