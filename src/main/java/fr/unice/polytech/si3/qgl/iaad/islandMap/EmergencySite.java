package fr.unice.polytech.si3.qgl.iaad.islandMap;

import java.awt.Point;

/**
 * @author romain
 * Created on 02/02/17.
 */
public class EmergencySite implements fr.unice.polytech.si3.qgl.iaad.future.EmergencySite
{
    private String id;
    private boolean isFound;

    public EmergencySite() { id = ""; }

    public EmergencySite(String id)
    {
        this.id = id;
        isFound = true;
    }

    @Override
    public String getId() { return id; }

    /*
     * Seront supprimés dans les jours qui suivent :
     * is Found()
     * EmergencySite()
     * isFound
     */

    boolean isFound() { return isFound; }
}
