package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.future.ICreek;

/**
 * Created by Théo on 13/02/2017.
 */
public class Creek implements ICreek {

    String id;

    public Creek(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return id;
    }
}
