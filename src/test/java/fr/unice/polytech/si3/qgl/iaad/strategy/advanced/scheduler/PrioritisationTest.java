package fr.unice.polytech.si3.qgl.iaad.strategy.advanced.scheduler;

import fr.unice.polytech.si3.qgl.iaad.engine.format.Context;
import fr.unice.polytech.si3.qgl.iaad.util.contract.Contract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Gaetan Vialon
 *         Created the 24/03/2017.
 */
public class PrioritisationTest {

    Contract context;
    IslandMap map;

    @Before
    public void setUp() throws Exception {
        map = new IslandMap();
        map.grow(Compass.N,0);
        map.grow(Compass.W,0);
        map.grow(Compass.S,10);
        map.grow(Compass.E,10);

    }
}