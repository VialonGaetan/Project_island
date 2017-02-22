package fr.unice.polytech.si3.qgl.iaad.format.json;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.format.Result;
import fr.unice.polytech.si3.qgl.iaad.board.Creek;
import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.board.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.resource.ResourceInformation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 06/02/2017.
 */
class JsonResult implements Result
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
        return creeks.toList().stream().map(o -> new Creek(o.toString())).collect(Collectors.toList());
    }

    @Override
    public List<EmergencySite> getSites()
    {
        JSONArray sites = extras.getJSONArray(JsonArguments.SITES.toString());
        return sites.toList().stream().map(o -> new fr.unice.polytech.si3.qgl.iaad.board.EmergencySite(o.toString())).collect(Collectors.toList());
    }

    @Override
    public List<ResourceInformation> getResourcesExplored()
    {
        return new ArrayList<>();
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
}
