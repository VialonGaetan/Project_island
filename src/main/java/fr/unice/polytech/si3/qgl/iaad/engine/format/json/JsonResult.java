package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceAmount;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceCondition;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 06/02/2017.
 */
public class JsonResult implements Result
{
    private final JSONObject extras;
    private final JSONObject data;

    JsonResult(JSONObject result)
    {
        this.data = result;
        extras = result.getJSONObject(JsonArguments.EXTRAS.toString());
    }

    @Override
    public int getCost()
    {
        return data.getInt(JsonArguments.COST.toString());
    }

    @Override
    public int getRange()
    {
        return extras.getInt(JsonArguments.RANGE.toString());
    }

    @Override
    public Element getFound()
    {
        return Element.valueOf(extras.get(JsonArguments.FOUND.toString()).toString());
    }

    @Override
    public List<Biome> getBiomes()
    {
        JSONArray biomes = extras.getJSONArray(JsonArguments.BIOMES.toString());
        return biomes.toList().stream().map(Object::toString).map(Biome::valueOf).collect(Collectors.toList());
    }

    @Override
    public List<Creek> getCreeks()
    {
        JSONArray creeks = extras.getJSONArray(JsonArguments.CREEKS.toString());
        return creeks.toList().stream().map(Object::toString).map(Creek::new).collect(Collectors.toList());
    }

    @Override
    public List<EmergencySite> getSites()
    {
        JSONArray sites = extras.getJSONArray(JsonArguments.SITES.toString());
        return sites.toList().stream().map(Object::toString).map(EmergencySite::new).collect(Collectors.toList());
    }

    @Override
    public List<ResourceInformation> getResourcesExplored()
    {
        List<ResourceInformation> resourceInformation = new ArrayList<>();
        JSONArray jsonArray = extras.getJSONArray(JsonArguments.RESOURCES.toString());
        for (int i = 0; i < jsonArray.length(); i++)
            resourceInformation.add(getResource(jsonArray.getJSONObject(i)));
        return resourceInformation;
    }

    @Override
    public int getExploitAmount()
    {
        return extras.getInt(JsonArguments.AMOUNT.toString());
    }

    @Override
    public int getTransformProduction()
    {
        return extras.getInt(JsonArguments.PRODUCTION.toString());
    }

    private ResourceInformation getResource(JSONObject jsonObject)
    {
        ResourceAmount resourceAmount = ResourceAmount.valueOf(jsonObject.get(JsonArguments.AMOUNT.toString()).toString());
        Resource resource = Resource.valueOf(jsonObject.get(JsonArguments.RESOURCE.toString()).toString());
        ResourceCondition resourceCondition = ResourceCondition.valueOf(jsonObject.get(JsonArguments.COND.toString()).toString());
        return new ResourceInformation(resource, resourceAmount, resourceCondition);
    }
}
