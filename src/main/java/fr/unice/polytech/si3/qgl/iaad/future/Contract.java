package fr.unice.polytech.si3.qgl.iaad.future;

import fr.unice.polytech.si3.qgl.iaad.resource.Resource;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Contract
{
    Resource getResource();

    int getAmount();
}
