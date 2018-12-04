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
import gui.CountryView;
import javafx.scene.paint.Color;
/**
 *
 * @author joonas
 */
public class CountryTest {
    Country c;
    @Before
    public void setUp() {
        c = new Country("florida", 2, 0, new Game());
        c.setView(new CountryView(c));
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
    
    @Test
    public void doesNotHaveOwnerBeforeInitializing() {
        assertEquals(c.hasOwner(), false);
    }
    
    @Test
    public void ownerSetProperly() {
        Player p = new Player();
        c.setOwner(p);
        Player g = c.getOwner();
        assertEquals(p, g);
    }
    
    @Test
    public void initiallyOnlyOneTroop() {
        assertEquals(c.getTroops(), 1);
    }
    
    @Test
    public void troopsSetCorrectly() {
        c.setTroops(15);
        assertEquals(c.getTroops(), 15);
    }
    
    @Test
    public void isNotSelectedInitially() {
        assertEquals(c.isSelected(), false);
    }
    
    @Test
    public void countryConstructorWithParametersSetsValuesRight() {
        Country co = new Country("florida", 3, 2, new Game());
        assertEquals(co.getName(), "florida");
        assertEquals(co.getTroopValue(), 3);
        assertEquals(co.getIndex(), 2);
    }
    
    @Test
    public void defaultColorIsBeige() {
        assertEquals(c.getDefaultColor(), Color.BEIGE);
    }
    
    @Test
    public void selectedProperly() {
        c.select();
        assertTrue(c.isSelected());
    }
}