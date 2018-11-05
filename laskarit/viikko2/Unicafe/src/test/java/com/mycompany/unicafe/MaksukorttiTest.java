package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanlataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        assertEquals(kortti.otaRahaa(300), false);
        
    }
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi(){
        assertEquals(kortti.otaRahaa(5), true);
    }
    
    @Test
    public void saldoToimii(){
        System.out.println(kortti.saldo());
        assertEquals(kortti.saldo(), 10);
    }
}
