package fr.unice.polytech.si3.qgl.iaad.engine.player.results;

import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Biome;

import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class ScanResultat {

    private Result result;

    public ScanResultat(Result result) {
        this.result = result;
    }

    public int getCost(){return result.getCost();}

    public List<Biome> getBiomes(){
        return result.getBiomes();
    }

    public List<Creek> getCreeks(){
        return result.getCreeks();
    }

    public List<EmergencySite> getSites(){
        return result.getSites();
    }
}
