package gameTests;


import game.Country;
import game.Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import gui.StartScreen;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class GameTest {
    Game g;
    StartScreen fss;
    @Before
    public void setUp() {
        g = new Game();
        fss = new StartScreen("");
        g.attachView(fss);
    }
    
    @Test
    public void initializationWorksCorrectly() {
        assertEquals(12, g.getCountries().size());
        assertEquals("COUNTRYSELECTION", g.getPhase());
        assertFalse(g.troopsLeftToSet());
        assertFalse(g.reduceTotalNumberOfTroopsLeftToDeploy());
        assertEquals(0, g.getNumberOfTroopsLeftToDeploy());
        assertEquals(12, g.getNumberOfCountriesRemainingToSelect());
        
        assertTrue(g.removeCountryToSet());
        assertEquals(11, g.getNumberOfCountriesRemainingToSelect());
    }
    
    
    
    @Test
    public void canGetCountryByName() {
        Country c = g.getCountryByName("Alabama");
        assertEquals(c.getName(), "Alabama");
    }
    
    public void countrySelection() {
        assertEquals(null, g.getSelectedCountry());
        g.selectCountry(new Country("Simola"));
        assertEquals("Simola", g.getSelectedCountry().getName());
        
    }
    
    public void playerSelection() {
        assertEquals(g.getCurrentPlayer().getColor().toString(), Color.GREEN);
    }
    /*
    @Test
    public void phaseMovements() {
        
    }
    
    @Test
    public void gameStarting() {
        g.start();
        assertEquals("COUNTRYSELECTION" ,g.getPhase());
    }*/
}