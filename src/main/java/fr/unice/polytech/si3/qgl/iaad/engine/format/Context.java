package fr.unice.polytech.si3.qgl.iaad.engine.format;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;

/**
 * operations on the context
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Context
{
    /**
     * return the number of men from the context
     * @return int value
     */
    int getNumberOfMen();

    /**
     * return the budget from the context
     * @return Budget value
     */
    Budget getBudget();

    /**
     * return the contract from the context
     * @return Contract value
     */
    Contract getContract();

    /**
     * return the heading of the crew or the drone from the context
     * @return Compass value
     */
    Compass getHeading();
}
