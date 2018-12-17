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
    /*
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
        g.nextDeploymentTurn();
        assertEquals("ATTACK", g.getPhase());
    }
    
    @Test
    public void startingCountrySelectionSetsReasonableDefaultValues() {
        g.startCountrySelection();
        assertEquals("COUNTRYSELECTION", g.getPhase());
        assertTrue(g.getCountries().size() == g.getNumberOfCountriesRemainingToSelect());
    }
    
    
    @Test
    public void canRemoveCountriesFromlist() {
        Country floor = new Country("Floor", 3, 0, g);
        Country blaar = new Country("blaar", 4, 1, g);
        g.startCountrySelection();
        System.out.println("countries: " + g.getNumberOfCountriesRemainingToSelect());
        assertTrue(g.removeCountryToSet());
        assertFalse(g.removeCountryToSet());
    }
    
    
    @Test
    public void gameStartsAndNoCountriesInitially() {
        g.start();
        assertEquals("COUNTRYSELECTION", g.getPhase());
        assertEquals(0, g.getCountries().size());
    }
    
    @Test
    public void advancingRoundChangesCurrentPlayer() {
        Player p = g.getCurrentPlayer();
        g.advanceRound();
        assertFalse(p.getName().equals(g.getCurrentPlayer().getName()));
    }
    
    @Test
    public void canAddCountries() {
        Country floor = new Country("Floor", 3, 0, g);
        Country blaar = new Country("blaar", 4, 1, g);
        assertEquals(2, g.getCountries().size());
    }
    
    @Test
    public void addedCountriesAddUpToNumberOfTroopsToDeploy() {
        Country floor = new Country("Floor", 3, 0, g);
        Country blaar = new Country("blaar", 4, 1, g);
        floor.setOwner(new Player(Color.GREEN));
        blaar.setOwner(new Player(Color.RED));
        g.startTroopDeployment();
        assertEquals(7, g.getNumberOfTroopsLeftToDeploy());
    }
    
    public void canReduceTroopsToDeploy() {
        Country floor = new Country("Floor", 3, 0, g);
        Country blaar = new Country("blaar", 4, 1, g);
        floor.setOwner(new Player(Color.GREEN));
        blaar.setOwner(new Player(Color.RED));
        g.startTroopDeployment();
        g.reduceTotalNumberOfTroopsLeftToDeploy();
        assertEquals(6, g.getNumberOfTroopsLeftToDeploy());
    }*/
}