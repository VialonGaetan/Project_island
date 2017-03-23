package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.util.resource.*;

import java.util.*;

/**
 * @author Alexandre Clement
 * @since 06/03/2017.
 */
public class MockedResult extends Result
{
    private Random random = new Random();
    private int cost = random.nextInt(50);
    private int range = random.nextInt(53);
    private Element found = Math.random() > 0.5 ? Element.GROUND : Element.OUT_OF_RANGE;

    @Override
    public int getCost()
    {
        return cost;
    }

    @Override
    protected int getRange()
    {
        return range;
    }

    @Override
    protected Element getFound()
    {
        return found;
    }

    @Override
    protected List<Biome> getBiomes()
    {
        List<Biome> biomes = new ArrayList<>(Arrays.asList(Biome.values()));
        int numberOfBiomes = 1 + random.nextInt(3);
        while (biomes.size() > numberOfBiomes)
            biomes.remove(random.nextInt(biomes.size()));
        return biomes;
    }

    @Override
    protected List<Creek> getCreeks()
    {
        return Math.random() > 0.1 ? Collections.EMPTY_LIST : Collections.singletonList(new Creek("creek_id"));
    }

    @Override
    protected List<EmergencySite> getSites()
    {
        return Math.random() > 0.01 ? Collections.EMPTY_LIST : Collections.singletonList(new EmergencySite("site_id"));
    }

    @Override
    protected List<ResourceInformation> getResourcesExplored()
    {
        PrimaryResource resource = getRandomEnumValue(PrimaryResource.values());
        ResourceAmount resourceAmount = getRandomEnumValue(ResourceAmount.values());
        ResourceCondition resourceCondition = getRandomEnumValue(ResourceCondition.values());
        return Collections.singletonList(new ResourceInformation(resource, resourceAmount, resourceCondition));
    }

    @Override
    protected int getExploitAmount()
    {
        return random.nextInt(40);
    }

    @Override
    protected int getTransformProduction()
    {
        return random.nextInt(20);
    }

    @Override
    protected List<GlimpseInformation> getGlimpseInformation() {
        Biome biome = getRandomEnumValue(Biome.values());
        int range = random.nextInt(4);
        double percentage = random.nextFloat();
        return Collections.singletonList(new GlimpseInformation(biome,percentage,range));
    }

    private <T> T getRandomEnumValue(T[] values)
    {
        return values[random.nextInt(values.length)];
    }


}