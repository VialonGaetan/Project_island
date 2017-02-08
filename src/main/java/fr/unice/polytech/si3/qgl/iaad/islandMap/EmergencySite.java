package fr.unice.polytech.si3.qgl.iaad.islandMap;

/**
 * @author romain
 * Created on 02/02/17.
 */
class EmergencySite
{
    private String id;

    EmergencySite(String id) { this.id = ""+id; }

    EmergencySite(EmergencySite newEmergencySite) { this(newEmergencySite.getId()); }

    String getId() { return id; }
}
