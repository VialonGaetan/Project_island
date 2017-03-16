package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Element;
import fr.unice.polytech.si3.qgl.iaad.util.map.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;
import fr.unice.polytech.si3.qgl.iaad.util.resource.ResourceInformation;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public abstract class Result
{
    public abstract int getCost();

    protected abstract int getRange();

    protected abstract Element getFound();

    protected abstract List<Biome> getBiomes();

    protected abstract List<Creek> getCreeks();

    protected abstract List<EmergencySite> getSites();

    protected abstract List<ResourceInformation> getResourcesExplored();

    protected abstract int getExploitAmount();

    protected abstract int getTransformProduction();

}
