package gameTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import game.Player;
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
    public void constructorWithColorSetsColorCorrectly() {
        Player p2 = new Player(Color.ALICEBLUE);
        assertEquals(p2.getColor().toString(), Color.ALICEBLUE.toString());
    }

    @Test
    public void initialNumberOfTroopsToSet() {
        int troops = p.getTroopsToSet();
        assertEquals(troops, 0);
    }

    @Test
    public void playerColorIsSet() {
        Player p2 = new Player(Color.RED);
        assertEquals(p2.getColor(), Color.RED);
    }

    @Test
    public void addingToTotalTroopPileTotalPile() {
        p.addTotalTroops(15);
        System.out.println("troops : " + p.getTotalTroops());
        assertEquals(15, p.getTotalTroops());
    }

    @Test
    public void addingToTotalTroopPileGrowsToSetPile() {
        p.addTotalTroops(15);
        assertEquals(15, p.getTroopsToSet());
    }
}
