package morpion;

import org.junit.*;
import static org.junit.Assert.*;

public class UnitTestSourceClasses {

    private Morpion m;
    private Plateau p;


    @Before public void setUp() {
        this.m = new Morpion();
        this.p = new Plateau();
    }

    @Test public void testSetCaseLigne() {
        p.placeSymbole(2,2);
        p.placeSymbole(0,0);
        p.placeSymbole(0,1);
        p.placeSymbole(1,0);
        p.placeSymbole(1,1);
        assertTrue(p.placeSymbole(2,0));
        assertEquals(Symbole.O, p.getWinningMark());
    }

    @Test public void testSetCaseIdentique() {
        p.placeSymbole(0, 0);
        assertFalse(p.placeSymbole(0,0));
        assertEquals(Symbole.X, p.getMarkAt(0,0));
    }

    @Test public void testSetCasecolonne() {
        p.placeSymbole(0,0);
        p.placeSymbole(1,0);
        p.placeSymbole(0,1);
        p.placeSymbole(1,1);
        assertTrue(p.placeSymbole(0,2));
        assertEquals(Symbole.X, p.getWinningMark());
    }

    @Test public void testSetCaseDiagonal() {
        p.placeSymbole(0,0);
        p.placeSymbole(0,1);
        p.placeSymbole(1,1);
        p.placeSymbole(1,2);
        assertTrue(p.placeSymbole(2,2));
        assertEquals(Symbole.X, p.getWinningMark());
    }



}
