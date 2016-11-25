package fr.unice.polytech.si3.qgl.iaad.actions;

/**
 * @author : Theo
 */

import com.sun.xml.internal.bind.AnyTypeAdapter;
import eu.ace_design.island.game.actions.Actions;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;

import java.util.List;
import java.util.ArrayList;

public class Strategy {

    private static List<Object> actions;
    private IslandMap map;
    private Drone d;

    public Strategy(Drone d, IslandMap map)
    {
        this.map= map;
        this.d=d;
    }
    /*
    * Permet de retourner une action en fonction du resultat de l'action précédente
    * @param : AreaResult
    * @return Actions
     */
    public Actions giveMeStrat(AreaResult result)
    {
        //ToDo
        return null;
    }

}
