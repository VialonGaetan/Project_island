package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.resource.Resource;

import java.util.Map;

/**
 * @author Theo Cholley
 * @since 13/02/2017.
 */
public interface Craft
{
    Map<Resource, Integer> getReagent();

    Map<Resource, Integer> getProducts();
}
