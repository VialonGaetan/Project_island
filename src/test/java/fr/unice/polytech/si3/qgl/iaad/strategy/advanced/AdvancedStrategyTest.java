package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.engine.format.Result;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.ArgActions;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;
import fr.unice.polytech.si3.qgl.iaad.strategy.Protocol;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.*;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexandre Clement
 * @since 05/03/2017.
 */
public class AdvancedStrategyTest
{
    private Protocol advancedStrategy() throws Exception
    {
        Context context = mock(Context.class);

        List<Contract> contracts = new ArrayList<>();
        contracts.add(new Contract(Resource.FISH, 1000));
        contracts.add(new Contract(Resource.GLASS, 50));

        when(context.getBudget()).thenReturn(10000);
        when(context.getHeading()).thenReturn(Compass.E);
        when(context.getNumberOfMen()).thenReturn(12);
        when(context.getContracts()).thenReturn(contracts);
        return new AdvancedStrategy(context, new IslandMap(), new Drone(context.getHeading()));
    }

    @Test
    public void run() throws Exception
    {
        for (int i = 0; i < 50; i++)
        {
            Protocol advanced = advancedStrategy();
            Decision decision = advanced.takeDecision();

            while (decision.getActionEnum() != ArgActions.STOP)
            {
                assertNotNull(decision);
                advanced = advanced.acknowledgeResults(randomResult());
                decision = advanced.takeDecision();
            }
        }
    }

    private Result randomResult()
    {
        Result result = mock(Result.class);
        when(result.getCost()).thenReturn(new Random().nextInt(50));
        when(result.getCreeks()).thenReturn(Math.random() > 0.1 ? Collections.EMPTY_LIST : Collections.singletonList(new Creek("creek_id")));
        when(result.getSites()).thenReturn(Math.random() > 0.01 ? Collections.EMPTY_LIST : Collections.singletonList(new EmergencySite("site_id")));
        when(result.getFound()).thenReturn(Math.random() > 0.5 ? Element.GROUND : Element.OUT_OF_RANGE);
        when(result.getRange()).thenReturn(10 + new Random().nextInt(42));
        when(result.getBiomes()).thenReturn(randomBiomes());
        return result;
    }

    private List<Biome> randomBiomes()
    {
        List<Biome> biomes = new ArrayList<>(Arrays.asList(Biome.values()));
        int numberOfBiomes = 1 + new Random().nextInt(2);
        while (biomes.size() > numberOfBiomes)
            biomes.remove(new Random().nextInt(biomes.size()));
        return biomes;
    }

}