import fr.unice.polytech.qgl.iaad.Greeter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by user on 07/11/2016.
 */
public class ExplorerTest {
    Greeter greeter;

    @Before
    public void defineContext() {
        greeter = new Greeter();
    }

    @Test
    public void sayHelloToSomeone() {
        String seb = greeter.sayHello("Sebastien");
        assertEquals("Hello, Sebastien!", seb);
    }

    @Test
    public void sayHelloToTheWorld() {
        String world = greeter.sayHello();
        assertEquals("Hello, World!", world);
    }

    @Test
    public void sayHelloEquivalence() {
        assertEquals(greeter.sayHello(), greeter.sayHello("World"));
    }
}
