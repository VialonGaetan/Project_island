package fr.unice.polytech.si3.qgl.iaad.strategy.common;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Fly;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Land;
import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Stop;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.map.Creek;
import fr.unice.polytech.si3.qgl.iaad.util.map.Direction;
import fr.unice.polytech.si3.qgl.iaad.util.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.util.workforce.Drone;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author romain
 * @since 25/03/2017.
 */
public class LandOnCreekTest
{
    private LandOnCreek landOnCreek;
    private Creek creek;
    private int people;

    @Before
    public void setUp()
    {
        people = 100;
        creek = new Creek("id");
        //landOnCreek = new LandOnCreek(null, creek, people);
    }
/*
    @Test
    public void takeDecision()
    {
        assertTrue(landOnCreek.takeDecision() instanceof Land);
    }

    @Test
    public void acknowledgeResults()
    {
        assertNull(landOnCreek.acknowledgeResults(null));
    }*/
}
