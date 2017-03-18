package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 18/03/2017.
 */
public class Basket extends EnumMap<Resource, Integer>
{
    public Basket()
    {
        super(Resource.class);
    }

    public Basket(Map<Resource, ? extends Integer> m)
    {
        super(m);
    }
}
