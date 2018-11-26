
import Game.Country;
import Game.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Game.Player;
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
    
    //@Test
    public void canGetCurrentPlayer() {
        Player p = g.getCurrentPlayer();
        assertEquals(p.getName(), "Player");
    }
    
}