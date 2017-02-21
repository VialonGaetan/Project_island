package fr.unice.polytech.si3.qgl.iaad.format;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.contract.Contract;

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

    Direction getHeading();
}
