package fr.unice.polytech.si3.qgl.iaad.strategy.advanced;

import fr.unice.polytech.si3.qgl.iaad.engine.player.actions.Decision;

import static org.junit.Assert.fail;

/**
 * @author Alexandre Clement
 * @since 18/03/2017.
 */
public class TimeOut implements Assertion
{
    private static final long TIME_OUT = 2000;
    private long previous;

    TimeOut()
    {
        this.previous = System.currentTimeMillis();
    }

    @Override
    public void valid(Decision decision)
    {
        long current = System.currentTimeMillis();
        if (current - previous > TIME_OUT)
            fail(String.format("Timeout : %d ms", current - previous));
        previous = current;
    }
}
