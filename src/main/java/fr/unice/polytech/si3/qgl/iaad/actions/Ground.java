package fr.unice.polytech.si3.qgl.iaad.actions;

import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Resource;

/**
 * Created by user on 15/11/2016.
 */
public abstract class Ground implements Action {

    Direction direction;
    int range, nbResource, nbResource1;
    Resource resource,resource1;
}
