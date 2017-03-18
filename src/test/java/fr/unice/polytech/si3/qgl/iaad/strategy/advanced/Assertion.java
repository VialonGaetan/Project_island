package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;

/**
 * @author Alexandre Clement
 * @since 18/03/2017.
 */
@FunctionalInterface
public interface Assertion
{
    void valid(Decision decision);
}
