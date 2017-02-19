package fr.unice.polytech.si3.qgl.iaad.islandMap;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EmergencySite emergencySite = (EmergencySite) o;

        return id.equals(emergencySite.id);
    }

    /*
     * Seront supprimés  :
     * is Found()
     * isFound
     */

    boolean isFound() { return isFound; }
}
