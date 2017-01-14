import fr.unice.polytech.si3.qgl.iaad.Direction;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertNotEquals(Direction.E, direction.getRight());
        assertNotEquals(Direction.W, direction.getRight());
        assertNotEquals(Direction.N, direction.getRight());
        assertNotEquals(direction.getBack(), direction.getRight());
        assertNotEquals(direction.getLeft(), direction.getRight());
        assertNotEquals(direction.getVecteur(), direction.getRight());
    }

    @Test
    public void getLeftTest()
    {
        Direction direction = Direction.E;
        assertEquals(Direction.N, direction.getLeft());
        assertNotEquals(Direction.E, direction.getLeft());
        assertNotEquals(Direction.W, direction.getLeft());
        assertNotEquals(Direction.S, direction.getLeft());
        assertNotEquals(direction.getBack(), direction.getLeft());
        assertNotEquals(direction.getRight(), direction.getLeft());
        assertNotEquals(direction.getVecteur(), direction.getLeft());
    }

    @Test
    public void getBackTest()
    {
        Direction direction = Direction.E;
        assertEquals(Direction.W, direction.getBack());
        assertNotEquals(Direction.E, direction.getBack());
        assertNotEquals(Direction.S, direction.getBack());
        assertNotEquals(Direction.N, direction.getBack());
        assertNotEquals(direction.getRight(), direction.getBack());
        assertNotEquals(direction.getLeft(), direction.getBack());
        assertNotEquals(direction.getVecteur(), direction.getBack());
    }

    @Test
    public void getVecteurTest()
    {
        Direction direction = Direction.E;
        assertEquals(new Point(1,0), direction.getVecteur());
        assertNotEquals(new Point(1,1), direction.getVecteur());
        assertNotEquals(new Point(0,0), direction.getVecteur());
        assertNotEquals(new Point(0,1), direction.getVecteur());
        direction = Direction.W;
        assertEquals(new Point(-1,0), direction.getVecteur());
        assertNotEquals(new Point(1,1), direction.getVecteur());
        assertNotEquals(new Point(0,0), direction.getVecteur());
        assertNotEquals(new Point(0,1), direction.getVecteur());
        direction = Direction.S;
        assertEquals(new Point(0,1), direction.getVecteur());
        assertNotEquals(new Point(1,1), direction.getVecteur());
        assertNotEquals(new Point(0,0), direction.getVecteur());
        assertNotEquals(new Point(1,0), direction.getVecteur());
        direction = Direction.N;
        assertEquals(new Point(0,-1), direction.getVecteur());
        assertNotEquals(new Point(1,1), direction.getVecteur());
        assertNotEquals(new Point(0,0), direction.getVecteur());
        assertNotEquals(new Point(0,1), direction.getVecteur());
    }
}