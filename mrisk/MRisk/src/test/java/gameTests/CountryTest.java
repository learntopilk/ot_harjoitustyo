package gameTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import game.Country;
import game.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import game.Player;
/**
 *
 * @author joonas
 */
public class CountryTest {
    Country c;
    @Before
    public void setUp() {
        c = new Country("florida", 2, 0, new Game());
    }
    
    @Test
    public void nameIsSetCorrectly() {
        String name = c.getName();
        assertEquals(name, "florida");
    }
    
    @Test
    public void ownerNotSetWhileInitializing() {
        Player owner = c.getOwner();
        assertEquals(owner, null);
        
    }
}