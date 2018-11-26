
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Game.Player;
import javafx.scene.paint.Color;

/**
 *
 * @author joonas
 */
public class PlayerTest {
    Player p;
    @Before
    public void setUp() {
        p = new Player();
    }
    
    @Test
    public void defaultNameIsPlayer() {
        String name = p.getName();
        System.out.println("name " + name);
        assertEquals(name, "Player");
    }
    
    @Test
    public void initialNumberOfTroopsToSet() {
        int troops = p.getTroopsToSet();
        assertEquals(troops, 10);
    }
    
    @Test
    public void playerColorIsSet() {
        Player p2 = new Player(Color.RED);
        assertEquals(p2.getColor(), Color.RED);
    }
}