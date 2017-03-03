package fr.unice.polytech.si3.qgl.iaad.engine.format;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 13/02/2017.
 */
public interface Context
{
    int getNumberOfMen();

    int getBudget();

    List<Contract> getContracts();

    Compass getHeading();
}
