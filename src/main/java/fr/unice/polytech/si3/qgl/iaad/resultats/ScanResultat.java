package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.board.Creek;
import fr.unice.polytech.si3.qgl.iaad.board.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.format.Result;

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
