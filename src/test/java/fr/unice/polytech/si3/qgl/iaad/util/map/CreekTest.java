package fr.unice.polytech.si3.qgl.iaad.util.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Théo on 27/02/2017.
 */
public class CreekTest {

    Creek creek;
    Creek otherCreek;

    @Before
    public void init(){
        this.creek= new Creek("ok");
        this.otherCreek = new Creek("pas ok");
    }

    @Test
    public void getIDTest(){
        assertEquals(creek.getId(),"ok");
        assertEquals(otherCreek.getId(),"pas ok");
    }

    @Test
    public void equalsTest(){
        assertFalse(this.creek.equals(otherCreek));
    }
}
