
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Explorer;

/**
 * @author Alexandre Clement
 *         Created the 20/11/2016.
 */
public class Drone {


    public Drone(Explorer explorer, int budget, Direction heading)
    {
    }

    public Action doAction()
    {
        return new Stop();
    }


    public void getResult(String results)
    {

    }
}
