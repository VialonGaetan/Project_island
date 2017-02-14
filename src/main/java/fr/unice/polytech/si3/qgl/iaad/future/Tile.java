package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.islandMap.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Creek;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Tile
{
    void addBiomes(List<Biome> biomes);

    void addCreeks(List<Creek> creeks);

    void addEmergencySites(List<EmergencySite> emergencySites);

    List<Biome> getBiomes();

    List<Creek> getCreeks();

    List<EmergencySite> getEmergencySites();

    boolean isAlreadyScanned();

    void setAsAlreadyScanned();

    boolean isAlreadyVisited();

    void setAsAlreadyVisited();

    boolean isAlreadyScouted();

    void setAsAlreadyScouted();

    boolean isAlreadyGlimpsed();

    void setAsAlreadyGlimpsed();
}
