
package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Actions.*;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Element;
import fr.unice.polytech.si3.qgl.iaad.Explorer;
import fr.unice.polytech.si3.qgl.iaad.IslandMap.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.result.AreaResult;
import fr.unice.polytech.si3.qgl.iaad.result.EchoResult;
import fr.unice.polytech.si3.qgl.iaad.result.ScanResult;

import java.awt.Point;
import java.util.*;

/**
 * @author Alexandre Clement
 *         Created the 20/11/2016.
 */
public class Drone {

    private static final int LOW_BUDGET = 200;

    private int budget;
    private int limitFound;
    private Point position;
    private Direction heading;
    private IslandMap map;
    private Explorer explorer;

    private final int[] limits = new int[4];

    private Direction actionDirection;
    private Direction searchDirection;
    private boolean scan;

    public Drone(Explorer explorer, int budget, Direction heading)
    {
        this.explorer = explorer;
        this.budget = budget;
        this.heading = heading;
        this.position = new Point(0, 0);
        this.map = new IslandMap();
        Arrays.fill(limits, -1);
        // map.setElement(0, 0, Element.START_DRONE);
        limits[(heading.ordinal() + 2) % 4] = 0;
        limitFound = 1;
    }

    public Action doAction()
    {
        if (budget < LOW_BUDGET)
            return new Stop();
        if (limitFound != 4)
            return doABarrelRoll();
        if (searchDirection == null) {
            searchDirection = Direction.values()[(heading.ordinal()+3)%4];
        }
        // map.printStatement();
        Action action = searchCreek();
        if (action != null)
            return action;
        return new Stop();
    }

    public void getResult(AreaResult results)
    {
        budget -= results.getCost();
        if (results instanceof EchoResult) {
            int range = results.getRange();
            Found found = Found.valueOf(results.getFound());
            switch (found) {
                case OUT_OF_RANGE:
                    limits[actionDirection.ordinal()] = range + getDirectionRange(actionDirection, position);
                    limitFound += 1;
                    // map.addPoints(actioDirection, range + getDirectionRange(actioDirection, position));
                    // setOcean(actioDirection, range);
                    break;
                case GROUND:
                    if (limits[actionDirection.ordinal()] < 0) {
                        map.addPoints(actionDirection, range);
                        limits[actionDirection.ordinal()] = -2;
                    }
                    // setOcean(actioDirection, range);
                    break;
            }
        } else if (results instanceof ScanResult) {
            if (results.getCreek() != null) explorer.setRapport("CREEK:"+results.getCreek());
        }
    }

    private Action searchCreek() {
        if (!scan) {
            scan = true;
            return new Scan();
        }
        if (heading == searchDirection) {
            return heading(Direction.values()[(actionDirection.ordinal() + 2) % 4]);
        }
        if (limits[heading.ordinal()] - getDirectionRange(heading, position) > 1) {
            actionDirection = heading;
            Point target = getPositionOfTarget(heading, 1);
            position.translate(target.x, target.y);
            return new Fly();
        }

        return heading(searchDirection);
    }

    private void setOcean(Direction direction, int range) {
        for (int i=0; i<range; i++) {
            Point target = setPositionOfTarget(direction, i+1, position);
            map.setElement(target.x, target.y, Element.OCEAN);
        }
    }

    private Action doABarrelRoll() {
        Direction right = Direction.values()[(heading.ordinal() + 1) % 4];
        Direction left = Direction.values()[(heading.ordinal() + 3) % 4];
        Action action;

        action = checkDimension(right, left);
        if (action != null)
                return action;

        if (limits[heading.ordinal()] - getDirectionRange(heading, position) > 1) {
            Point target = getPositionOfTarget(heading, 1);
            position.translate(target.x, target.y);
            return new Fly();
        }

        action = heading(right);
        if (action != null)
            return action;
        action = heading(left);
        if (action != null)
            return action;

        return new Stop();
    }

    private Action checkDimension(Direction right, Direction left) {
        if (limits[heading.ordinal()] < 0 && actionDirection == null)
            return new Echo(actionDirection = heading);
        if (limits[right.ordinal()] < 0 && (actionDirection == heading || actionDirection == null))
            return new Echo(actionDirection = right);
        if (limits[left.ordinal()] < 0 && (actionDirection == right || actionDirection == heading || actionDirection == null))
            return new Echo(actionDirection = left);
        actionDirection = null;
        return null;
    }

    private Action heading(Direction direction) {
        if (limits[direction.ordinal()] - getDirectionRange(direction, position) > 1) {
            Point target = getPositionOfTarget(heading, 1);
            position.translate(target.x, target.y);
            heading = direction;
            target = getPositionOfTarget(heading, 1);
            position.translate(target.x, target.y);
            return new Heading(heading);
        }
        return null;
    }

    private Point getPositionOfTarget(Direction direction, int range) {
        return setPositionOfTarget(direction, range, new Point(0, 0));
    }

    private Point setPositionOfTarget(Direction direction, int range, Point init) {
        Point point = (Point) init.clone();
        switch (direction) {
            case N:
                point.translate(0, -range);
                return point;
            case E:
                point.translate(range, 0);
                return point;
            case S:
                point.translate(0, range);
                return point;
            case W:
                point.translate(-range, 0);
                return point;
            default:
                return point;
        }
    }

    private int getDirectionRange(Direction direction, Point position) {
        switch (direction) {
            case N:
                return -position.y;
            case E:
                return position.x;
            case S:
                return position.y;
            case W:
                return -position.x;
            default:
                return 0;
        }
    }
}
