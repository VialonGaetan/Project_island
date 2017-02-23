package fr.unice.polytech.si3.qgl.iaad.resultats;

import fr.unice.polytech.si3.qgl.iaad.Biome;
import fr.unice.polytech.si3.qgl.iaad.board.Creek;
import fr.unice.polytech.si3.qgl.iaad.board.EmergencySite;
import fr.unice.polytech.si3.qgl.iaad.format.json.JsonResult;

import java.util.List;

/**
 * @author Gaetan Vialon
 *         Created the 23/02/2017.
 */
public class ScanResultat {

    JsonResult result;

    ScanResultat(JsonResult result) {
        this.result = result;
    }

    public int getCots(){return result.getCost();}

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
