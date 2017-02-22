package fr.unice.polytech.si3.qgl.iaad.board;

import fr.unice.polytech.si3.qgl.iaad.future.ICreek;

/**
 * Created by Théo on 13/02/2017.
 */
public class Creek implements ICreek {

    private final String id;

    public Creek(String id){
        this.id=id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Creek creek = (Creek) o;

        return id.equals(creek.id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
