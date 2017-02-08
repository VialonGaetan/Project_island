package fr.unice.polytech.si3.qgl.iaad.islandMap;

import java.awt.Point;

/**
 * @author romain
 * Created on 02/02/17.
 */
class EmergencySite
{
    private String id;
    private boolean isFound;

    EmergencySite(String id)
    {
        this.id = "" + id;
        isFound = true;
    }

    EmergencySite() { id = ""; }

    String getId() { return id; }

    boolean isFound() { return isFound; }
}
