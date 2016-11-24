import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 *         Created the 24/11/2016.
 */
public class DirectionTest
{

    @Test
    public void getRightTest()
    {
        Direction direction = Direction.E;
        assertEquals(Direction.S, direction.getRight());
    }

    @Test
    public void getLeftTest()
    {
        Direction direction = Direction.E;
        assertEquals(Direction.N, direction.getLeft());
    }

    @Test
    public void getBackTest()
    {
        Direction direction = Direction.E;
        assertEquals(Direction.W, direction.getBack());
    }
}