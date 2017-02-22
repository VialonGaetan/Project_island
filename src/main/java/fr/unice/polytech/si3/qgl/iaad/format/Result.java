package fr.unice.polytech.si3.qgl.iaad.format;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.board.Creek;
import fr.unice.polytech.si3.qgl.iaad.board.Element;
import fr.unice.polytech.si3.qgl.iaad.board.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.resource.ResourceInformation;

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
