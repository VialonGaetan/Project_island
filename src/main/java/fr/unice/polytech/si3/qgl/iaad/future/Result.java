package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.Resource;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;

import java.util.List;
import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Result
{
    int getCost();

    int getRange();

    Element getFound();

    List<Biome> getBiomes();

    List<Creek> getCreeks();

    List<EmergencySite> getSites();

    Map<Resource, Integer> getResourcesExplored();

    int getExploitAmount();

    int getTransformProduction();
}
