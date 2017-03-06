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

    @Override
    public int getCost()
    {
        return random.nextInt(50);
    }

    @Override
    protected int getRange()
    {
        return 10 + random.nextInt(42);
    }

    @Override
    protected Element getFound()
    {
        return Math.random() > 0.5 ? Element.GROUND : Element.OUT_OF_RANGE;
    }

    @Override
    protected List<Biome> getBiomes()
    {
        List<Biome> biomes = new ArrayList<>(Arrays.asList(Biome.values()));
        int numberOfBiomes = 1 + random.nextInt(2);
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
        Resource resource = getRandomEnumValue(Resource.values());
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

    private <T> T getRandomEnumValue(T[] values)
    {
        return values[random.nextInt(values.length)];
    }
}