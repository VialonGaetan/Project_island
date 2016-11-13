package fr.unice.polytech.qgl.iaad;

/**
 * @author Alexandre Clement
 *         Created the 12/11/2016.
 */
public class Drone {

    private int x;
    private int y;
    private int budget;
    private Direction direction;

    public Drone setDirection(String heading) {
        this.direction = Direction.valueOf(heading);
        return this;
    }

    public Drone setBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public Decision takeFirstDecision() {
        if (budget < 3) return new Decision().putAction(Action.STOP);
        return new Decision().putAction(Action.ECHO).putParameters(direction);
    }

    public Decision takeDecision(Decision decision, AnswersQuery answersQuery) {
        budget -= answersQuery.GetCost();
        if (budget < 3) return new Decision().putAction(Action.STOP);
        if (decision.getAction() == Action.ECHO)
        {
            if (answersQuery.getExtras().getFound() == Found.GROUND)
                return new Decision().putAction(Action.FLY);
            else if (answersQuery.getExtras().getFound() == Found.OUT_OF_RANGE)
            {
                if (answersQuery.getExtras().getRange() < 1)
                    return new Decision().putAction(Action.STOP);
                else
                    return new Decision().putAction(Action.FLY);
            }
        } else if (decision.getAction() == Action.FLY)
            return new Decision().putAction(Action.ECHO).putParameters(direction);

        return new Decision().putAction(Action.STOP);
    }
}

