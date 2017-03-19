package fr.unice.polytech.si3.qgl.iaad.engine.format.json;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgResult;
import fr.unice.polytech.si3.qgl.iaad.engine.player.results.Result;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.util.resource.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 06/02/2017.
 */
public class JsonResult extends Result {
    private final JSONObject extras;
    private final JSONObject data;

    public JsonResult(JSONObject result) {
        this.data = result;
        extras = result.getJSONObject(JsonArguments.EXTRAS.toString());
    }

    @Override
    public int getCost() {
        return data.getInt(JsonArguments.COST.toString());
    }

    @Override
    public int getRange() {
        return extras.getInt(JsonArguments.RANGE.toString());
    }

    @Override
    public Element getFound() {
        return Element.valueOf(extras.get(JsonArguments.FOUND.toString()).toString());
    }

    @Override
    public List<Biome> getBiomes() {
        JSONArray biomes = extras.getJSONArray(JsonArguments.BIOMES.toString());
        return retrievesData(biomes).stream().map(Biome::valueOf).collect(Collectors.toList());
    }

    @Override
    public List<Creek> getCreeks() {
        JSONArray creeks = extras.getJSONArray(JsonArguments.CREEKS.toString());
        return retrievesData(creeks).stream().map(Creek::new).collect(Collectors.toList());
    }

    @Override
    public List<EmergencySite> getSites() {
        JSONArray sites = extras.getJSONArray(JsonArguments.SITES.toString());
        return retrievesData(sites).stream().map(EmergencySite::new).collect(Collectors.toList());
    }

    @Override
    public List<ResourceInformation> getResourcesExplored() {
        List<ResourceInformation> resourceInformation = new ArrayList<>();
        JSONArray jsonArray = extras.getJSONArray(JsonArguments.RESOURCES.toString());
        for (int i = 0; i < jsonArray.length(); i++)
            resourceInformation.add(getResource(jsonArray.getJSONObject(i)));
        return resourceInformation;
    }

    @Override
    public int getExploitAmount() {
        return extras.getInt(JsonArguments.AMOUNT.toString());
    }

    @Override
    public int getTransformProduction() {
        return extras.getInt(JsonArguments.PRODUCTION.toString());
    }


    private ResourceInformation getResource(JSONObject jsonObject)
    {
        ResourceAmount resourceAmount = ResourceAmount.valueOf(jsonObject.get(JsonArguments.AMOUNT.toString()).toString());
        Resource resource = Resource.valueOf(jsonObject.get(JsonArguments.RESOURCE.toString()).toString());
        ResourceCondition resourceCondition = ResourceCondition.valueOf(jsonObject.get(JsonArguments.COND.toString()).toString());
        return new ResourceInformation(resource, resourceAmount, resourceCondition);
    }

    public List<GlimpseInformation> getGlimpseInformation(){
        List<GlimpseInformation> glimpseInformations = new ArrayList<>();
        JSONArray report = extras.getJSONArray(ArgResult.REPORT.getName());
        for (int i = 0; i < report.length(); i++) {
            for (int j = 0; j < report.getJSONArray(i).length(); j++) {
                if (i < 2)
                    glimpseInformations.add(new GlimpseInformation(Biome.valueOf(report.getJSONArray(i).getJSONArray(j).getString(0)),report.getJSONArray(i).getJSONArray(j).getDouble(1),i+1));
                else
                    glimpseInformations.add(new GlimpseInformation(Biome.valueOf(report.getJSONArray(i).getString(j)),i+1));
            }

        }
        return glimpseInformations;
    }

    private static List<String> retrievesData(JSONArray jsonArray)
    {
        List<String> list = new ArrayList<>();
        for (Object o : jsonArray)
        {
            list.add(o.toString());
        }
        return list;
    }
}
