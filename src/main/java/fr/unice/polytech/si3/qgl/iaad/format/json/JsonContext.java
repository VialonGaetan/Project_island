package fr.unice.polytech.si3.qgl.iaad.format.json;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.format.Context;
import fr.unice.polytech.si3.qgl.iaad.future.Contract;
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
    private final Direction heading;
    private final int men;
    private final List<Contract> contracts;

    JsonContext(JSONObject jsonObject)
    {
        heading = Direction.valueOf(jsonObject.get(JsonArguments.HEADING.toString()).toString());
        budget = jsonObject.getInt(JsonArguments.BUDGET.toString());
        men = jsonObject.getInt(JsonArguments.MEN.toString());
        contracts = retrievesContracts(jsonObject);
    }

    private List<Contract> retrievesContracts(JSONObject jsonObject)
    {
        return new ArrayList<>();
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
    public Direction getHeading()
    {
        return heading;
    }
}
