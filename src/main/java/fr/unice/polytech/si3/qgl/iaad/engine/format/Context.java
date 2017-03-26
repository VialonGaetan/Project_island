package fr.unice.polytech.si3.qgl.iaad.engine.format;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Context
{
    int getNumberOfMen();

    Budget getBudget();

    Contract getContract();

    Compass getHeading();
}
