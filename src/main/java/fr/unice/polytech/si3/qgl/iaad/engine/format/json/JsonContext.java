package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.contract.StandardContract;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/11/2016.
 * @author Gaetan VIALON
 */
class JsonContext implements Context
{
    private final int budget;
    private final Compass heading;
    private final int men;
    private final List<Contract> contracts;

    JsonContext(JSONObject jsonObject)
    {
        heading = Compass.valueOf(jsonObject.get(JsonArguments.HEADING.toString()).toString());
        budget = jsonObject.getInt(JsonArguments.BUDGET.toString());
        men = jsonObject.getInt(JsonArguments.MEN.toString());
        contracts = retrievesContracts(jsonObject);
    }

    private List<Contract> retrievesContracts(JSONObject jsonObject)
    {
        List<Contract> retrievedContracts = new ArrayList<>();
        JSONObject jsonContract;
        Resource resource;
        int amount;
        Contract contract;
        JSONArray jsonArray = jsonObject.getJSONArray(JsonArguments.CONTRACTS.toString());
        for (int i = 0; i < jsonArray.length(); i++)
        {
            jsonContract = jsonArray.getJSONObject(i);
            resource = Resource.valueOf(jsonContract.get(JsonArguments.RESOURCE.toString()).toString());
            amount = jsonContract.getInt(JsonArguments.AMOUNT.toString());
            contract = new StandardContract(resource, amount);
            retrievedContracts.add(contract);
        }
        return retrievedContracts;
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
    public List<Contract> getContracts()
    {
        return contracts;
    }

    @Override
    public Compass getHeading()
    {
        return heading;
    }
}
