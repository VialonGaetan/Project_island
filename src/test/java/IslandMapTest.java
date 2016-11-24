// import eu.ace_design.island.map.IslandMap;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.islandMap.AddPointsException;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 *         Created the 24/11/2016.
 */
public class IslandMapTest {

    private IslandMap map;

    @Before
    public void before() {
        map = new IslandMap();
    }

    @Test
    public void IslandMapConstructorTest() {
        
        assertEquals(new Point(0,0), map.getDroneCoordinates());
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * On agrandie la carte pour ajouter un élément Ground
     * @throws AddPointsException
     */
    @Test
    public void GroundTest() throws AddPointsException {
        
        map.ground(Direction.E, 10);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(11, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * On ajoute une limite de taille à la carte dans la direction donnée
     * @throws AddPointsException
     */
    @Test
    public void OutOfRangeTest() throws AddPointsException {
        
        map.setOutOfRange(Direction.E, 10);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * On bouge puis on ajoute une limite de taille à la carte dans la direction donnée
     * Puis on bouge encore et on vérifie le nombre de cases disponible avant les limites de carte
     * @throws AddPointsException
     */
    @Test
    public void MoveThenOutOfRangeTest() throws AddPointsException {
        
        for (int i=0; i<10; i++)
            map.moveDroneCorrectly(Direction.E);
        map.setOutOfRange(Direction.E, 10);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(10, map.getNumberOfAvailablePoints(Direction.W));

        for (int i=0; i<5; i++)
            map.moveDroneCorrectly(Direction.E);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(5, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(15, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * On peut ajoutée un élément Ground puis un élément Out of range sans problème
     * @throws AddPointsException
     */
    @Test
    public void FoundTest() throws AddPointsException {
        
        map.ground(Direction.E, 10);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(11, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
        map.setOutOfRange(Direction.E, 50);
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.N));
        assertEquals(50, map.getNumberOfAvailablePoints(Direction.E));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.S));
        assertEquals(0, map.getNumberOfAvailablePoints(Direction.W));
    }

    /**
     * Renvoie true si l'élément est présent à la position choisie, false sinon
     */
    @Test
    public void hasElementTest() throws AddPointsException {
        
        map.setElement(Element.GROUND);
        assertTrue(map.hasElement(0, 0, Element.GROUND));
    }

    // TODO: 24/11/2016
    /**
     * On bouge puis on place un élément
     * @throws AddPointsException
     */
    @Test
    public void setElementTest() throws AddPointsException {
        
        for (int i=0; i<4; i++)
            map.moveDroneCorrectly(Direction.E);
        map.setElement(Element.GROUND);
        assertTrue(map.hasElement(4, 0, Element.GROUND));
    }
    
    // TODO: 24/11/2016  
    /**
     * On place plusieurs éléments
     * @throws AddPointsException
     */
    @Test
    public void setMultipleElementsTest() throws AddPointsException {
        
        map.setElement(Element.GROUND);
        map.setElement(Element.BEACH);
        map.setElement(Element.CREEK);
        assertTrue(map.hasElement(0, 0, Element.GROUND));
        assertTrue(map.hasElement(0, 0, Element.BEACH));
        assertTrue(map.hasElement(0, 0, Element.CREEK));
        assertFalse(map.hasElement(0, 0, Element.WOOD));
    }
    
    /**
     * On récupère l'élément à la position choisie
     * @throws AddPointsException
     */
    @Test
    public void getElementTest() throws AddPointsException {
        
        map.ground(Direction.E, 10);
        assertEquals(Element.UNKNOWN, map.getElement(5, 0));
        assertEquals(Element.GROUND, map.getElement(11, 0));
    }

    /**
     * On déplace le drone de une case dans la direction donnée
     */
    @Test
    public void moveDroneTest() {
        
        map.moveDroneCorrectly(Direction.E);
        assertEquals(new Point(1, 0), map.getDroneCoordinates());
        map.moveDroneCorrectly(Direction.S);
        assertEquals(new Point(1, 1), map.getDroneCoordinates());
        map.moveDroneCorrectly(Direction.W);
        assertEquals(new Point(0, 1), map.getDroneCoordinates());
        map.moveDroneCorrectly(Direction.N);
        assertEquals(new Point(0, 0), map.getDroneCoordinates());
    }


    /**
     * Rien n'empeche de bouger le drône hors de la range
     * future improvement : empecher cela
     * @throws AddPointsException
     */
    @Test
    public void moveDroneOutOfRange() throws AddPointsException {
        
        map.setOutOfRange(Direction.E, 3);
        for (int i=0; i<4; i++)
            map.moveDroneCorrectly(Direction.E);
    }

    // TODO: 24/11/2016
    /**
     * Lorsque l'on veut placer un élément Ground au dela des limites de la carte, retourne une erreur
     * @throws AddPointsException
     */
    @Test(expected = AddPointsException.class)
    public void AddPointsExceptionOnGroundTest() throws AddPointsException{
        
        map.setOutOfRange(Direction.E, 10);
        map.ground(Direction.E, 20);
    }

    // TODO: 24/11/2016
    /**
     * Lorsque l'on veut placer un élément au dela des limites de la carte, retourne une erreur
     * @throws AddPointsException
     */
    @Test(expected = AddPointsException.class)
    public void AddPointsExceptionOnElementTest() throws AddPointsException{
        
        map.setOutOfRange(Direction.E, 3);
        for (int i=0; i<4; i++)
            map.moveDroneCorrectly(Direction.E);
        assertEquals(new Point(4, 0), map.getDroneCoordinates());
        map.setElement(Element.BEACH);
    }

    /**
     * Renvoie true si la carte a une dimension finie dans la direction choisie i.e ne peut plus être agrandie dans cette direction
     * Sinon, renvoie false
     * @throws AddPointsException
     */
    @Test
    public void isDirectionFinished() throws AddPointsException {
        
        map.setOutOfRange(Direction.E, 10);
        map.setOutOfRange(Direction.N, 0);
        assertTrue(map.isDirectionFinished(Direction.N));
        assertTrue(map.isDirectionFinished(Direction.E));

        map.ground(Direction.S, 10);

        assertFalse(map.isDirectionFinished(Direction.S));
        assertFalse(map.isDirectionFinished(Direction.W));

        map.setOutOfRange(Direction.S, 11);

        assertTrue(map.isDirectionFinished(Direction.S));
    }

    /**
     * Renvoie true si la carte a des dimensions finies dans toutes les directions, false sinon
     * @throws AddPointsException
     */
    @Test
    public void isFinished() throws AddPointsException {
        
        map.setOutOfRange(Direction.E, 10);
        map.setOutOfRange(Direction.N, 0);
        assertFalse(map.isFinished());

        map.setOutOfRange(Direction.W, 10);
        map.setOutOfRange(Direction.S, 20);
        assertTrue(map.isFinished());
    }

    /**
     * On peut set plusieurs fois une direction sans retourner d'erreur si les valeurs coincide
     * @throws AddPointsException
     */
    @Test
    public void setOutOfRangeExceptionTest() throws AddPointsException {
        
        map.setOutOfRange(Direction.E, 1);
        map.setOutOfRange(Direction.E, 1);
        assertEquals(1, map.getNumberOfAvailablePoints(Direction.E));
    }

    /**
     * On peut sortir de la carte mais ajouter des éléments à l'intérieur sans problème
     * @throws AddPointsException
     */
    @Test
    public void weirdTest() throws AddPointsException {
        
        map.setOutOfRange(Direction.E, 1);
        map.setOutOfRange(Direction.W, 1);
        map.moveDroneCorrectly(Direction.E);
        map.moveDroneCorrectly(Direction.E);
        map.ground(Direction.W, 2);
    }
}