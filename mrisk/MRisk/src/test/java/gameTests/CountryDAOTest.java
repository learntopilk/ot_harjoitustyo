/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameTests;

import daos.CountryDAO;
import game.Country;
import game.Game;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author joonas
 */
public class CountryDAOTest {

    Game g;
    CountryDAO c;

    @Before
    public void setup() {
        g = new Game();
        c = new CountryDAO(g);
    }
    
    @Test
    public void countriesAreCreated() {
        List<Country> a = c.readCountries();
        assertEquals (12, a.size());
    }
}
