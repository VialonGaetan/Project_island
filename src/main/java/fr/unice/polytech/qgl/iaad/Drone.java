package fr.unice.polytech.qgl.iaad;

/**
 * @author Alexandre Clement
 *         Created the 12/11/2016.
 */
public class Drone {

    private int x;
    private int y;
    private Direction direction;

    public Drone setDirection(String heading) {
        this.direction = Direction.valueOf(heading);
        return this;
    }

    public Decision takeFirstDecision() {
        return new Decision().putAction(Action.ECHO).putParameters(direction);
    }

    public Decision takeDecision(Decision decision, AnswersQuery answersQuery) {
        return new Decision().putAction(Action.STOP);
    }
}

