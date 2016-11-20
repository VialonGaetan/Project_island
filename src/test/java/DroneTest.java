import fr.unice.polytech.si3.qgl.iaad.Actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.aerial.Drone;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.FlyResult;
import org.json.JSONObject;
import org.junit.Test;

/**
 * @author Alexandre Clement
 *         Created the 20/11/2016.
 */
public class DroneTest {
    public final static String OOR = "OUT_OF_RANGE";
    public final static String GR = "GROUND";

    @Test
    public void initTest() {
        int count = 0;
        Direction heading = Direction.E;
        Drone drone = new Drone(new Explorer(), 10000, heading);
        for (Action action = drone.doAction(); (!(action instanceof Stop)); action = drone.doAction()) {
            if (action instanceof Echo) {
                if (count % 20 == 1) {
                    count += 1;
                    drone.getResult(new EchoResult(getJson(2, 52, OOR).toString()));
                }
                else {
                    count += 1;
                    drone.getResult(new EchoResult(getJson(2, 20, GR).toString()));
                }
            }
            if (action instanceof Fly) {
                count += 1;
                drone.getResult(new FlyResult("\n" +
                        "    { \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }\n"));
            }
            if (action instanceof Heading) {
                count = 1;
                heading = ((Heading) action).direction;
                drone.getResult(new FlyResult("\n" +
                        "    { \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }\n"));
            }
        }
    }

    private JSONObject getJson(int cost, int range, String found) {
        return new JSONObject().put("cost", cost).put("extras", new JSONObject().put("range", range).put("found", found)).put("status", "OK");
    }
}
