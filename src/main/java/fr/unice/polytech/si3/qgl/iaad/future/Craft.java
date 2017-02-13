package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.Resource;

import java.util.Map;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Craft
{
    Map<Resource, Integer> getReagent();

    Map<Resource, Integer> getProducts();
}
