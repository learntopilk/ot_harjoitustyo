/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameTests;

import game.Continent;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author joonas
 */
public class ContinentTest {
    Continent c;
    @Before
    public void setUp() {
        c = new Continent();
    }
    
    @Test
    public void continentExists() {
        assertNotNull(c);
    }
}

