package fr.unice.polytech.si3.qgl.iaad.engine.format;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;

import java.util.List;

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

    List<ResourceInformation> getResourcesExplored();

    int getExploitAmount();

    int getTransformProduction();
}
