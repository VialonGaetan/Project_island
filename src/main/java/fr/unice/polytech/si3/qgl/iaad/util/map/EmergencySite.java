package fr.unice.polytech.si3.qgl.iaad.util.map;

/**
 * @author romain
 * Created on 02/02/17.
 */
public class EmergencySite
{
    private String id;

    public EmergencySite(String id) { this.id = id; }

    public String getId() { return id; }

    @Override
    public int hashCode() { return id != null ? id.hashCode() : 0; }

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
}
