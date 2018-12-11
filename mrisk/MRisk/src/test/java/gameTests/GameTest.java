package gameTests;


import game.Country;
import game.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import game.Player;
import javafx.scene.paint.Color;
import java.util.*;

/**
 *
 * @author joonas
 */
public class GameTest {
    Game g;
    @Before
    public void setUp() {
        g = new Game();
    }
    
    @Test
    public void countriesInitialized() {
        List<Country> l = g.getCountries();
        assertNotNull(l);
    }
    
    @Test
    public void canGetCurrentPlayer() {
        Player p = g.getCurrentPlayer();
        assertEquals("Player 0x008000ff", p.getName());
    }
    
    @Test
    public void noCountrySelectedInitially() {
        Country c = g.getSelectedCountry();
        assertEquals(null, c);
    }
    
    @Test
    public void initialPhaseIsCountrySelection() {
        assertEquals(g.getPhase(), "COUNTRYSELECTION");
    }
    
    @Test
    public void attackPhaseStartsNicely() {
        g.start();
        assertEquals("ATTACK", g.getPhase());
    }
    
    @Test
    public void startingCountrySelectionSetsReasonableDefaultValues() {
        g.startCountrySelection();
        assertEquals("COUNTRYSELECTION", g.getPhase());
        assertTrue(0 < g.getNumberOfCountriesRemainingToSelect());
    }
    
    
    @Test
    public void gameStartsAndNumberOfCountriesToSelectDecreases() {
        g.start();
        //int countries = g.getNumberOfCountriesRemainingToSelect();
        assertTrue(g.removeCountryToSet());
    }
    
    @Test
    public void advancingRoundChangesCurrentPlayer() {
        Player p = g.getCurrentPlayer();
        g.advanceRound();
        assertFalse(p.getName().equals(g.getCurrentPlayer().getName()));
    }
    
}