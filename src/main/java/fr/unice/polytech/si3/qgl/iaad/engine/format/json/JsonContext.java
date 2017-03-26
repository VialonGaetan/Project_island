package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Budget;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceCategory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created on 10/11/2016.
 *
 * @author Gaetan VIALON
 */
class JsonContext implements Context
{
    private final Budget budget;
    private final Compass heading;
    private final int men;
    private final Contract contract;

    JsonContext(JSONObject jsonObject)
    {
        heading = Compass.valueOf(jsonObject.get(JsonArguments.HEADING.toString()).toString());
        budget = new Budget(jsonObject.getInt(JsonArguments.BUDGET.toString()));
        men = jsonObject.getInt(JsonArguments.MEN.toString());

        contract = new Contract();
        retrievesContract(jsonObject);
    }

    private void retrievesContract(JSONObject jsonObject)
    {
        JSONObject jsonContract;
        int amount;
        String name;
        Resource resource;
        JSONArray jsonArray = jsonObject.getJSONArray(JsonArguments.CONTRACTS.toString());

        for (int i = 0; i < jsonArray.length(); i++)
        {
            jsonContract = jsonArray.getJSONObject(i);
            name = jsonContract.get(JsonArguments.RESOURCE.toString()).toString();
            resource = Resource.valueOf(name);
            amount = jsonContract.getInt(JsonArguments.AMOUNT.toString());
            if (resource.getCategory() == ResourceCategory.PRIMARY)
                contract.addContract(new PrimaryContract(PrimaryResource.valueOf(name), amount));
            else
                contract.addContract(new ManufacturedContract(Manufactured.valueOf(name), amount));
        }
    }

    @Override
    public int getNumberOfMen()
    {
        return men;
    }

    @Override
    public Budget getBudget()
    {
        return budget;
    }

    @Override
    public Compass getHeading()
    {
        return heading;
    }

    @Override
    public Contract getContract()
    {
        return contract;
    }
}
