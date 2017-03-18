package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created on 10/11/2016.
 *
 * @author Gaetan VIALON
 */
class JsonContext implements Context
{
    private final int budget;
    private final Compass heading;
    private final int men;
    private final Contract contract;

    JsonContext(JSONObject jsonObject)
    {
        heading = Compass.valueOf(jsonObject.get(JsonArguments.HEADING.toString()).toString());
        budget = jsonObject.getInt(JsonArguments.BUDGET.toString());
        men = jsonObject.getInt(JsonArguments.MEN.toString());
        contract = retrievesContract(jsonObject);
    }

    private Contract retrievesContract(JSONObject jsonObject)
    {
        JSONObject jsonContract;
        Resource resource;
        int amount;
        Basket resourceInContract = new Basket();
        JSONArray jsonArray = jsonObject.getJSONArray(JsonArguments.CONTRACTS.toString());
        for (int i = 0; i < jsonArray.length(); i++)
        {
            jsonContract = jsonArray.getJSONObject(i);
            resource = Resource.valueOf(jsonContract.get(JsonArguments.RESOURCE.toString()).toString());
            amount = jsonContract.getInt(JsonArguments.AMOUNT.toString());
            resourceInContract.put(resource, amount);
        }
        return new Contract(resourceInContract);
    }

    @Override
    public int getNumberOfMen()
    {
        return men;
    }

    @Override
    public int getBudget()
    {
        return budget;
    }

    @Override
    public Contract getContract()
    {
        return contract;
    }

    @Override
    public Compass getHeading()
    {
        return heading;
    }
}
