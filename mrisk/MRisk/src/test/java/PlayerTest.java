/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Game.Player;
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
}