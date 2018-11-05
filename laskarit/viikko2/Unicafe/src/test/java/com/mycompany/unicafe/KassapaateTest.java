/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.unicafe.Kassapaate;

/**
 *
 * @author joonas
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void oikeaMaaraRahaa() {
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void oikeaMaaraLounaita() {
        assertEquals(kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void ostoToimiiKunMaksuRiittava() {
        assertEquals(kassa.syoEdullisesti(kortti), true);
    }
    
    @Test
    public void lounaidenMaaraKasvaaKunMaksuRiittava(){
       kassa.syoEdullisesti(kortti);
       kassa.syoMaukkaasti(kortti);
       assertEquals(kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty(), 2);
    }
    
    @Test
    public void ostoEiToimiKunMaksuEiRiittävä(){
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        
        assertEquals(kassa.syoEdullisesti(kortti), false);
    }
    
    @Test
    public void kortilleLatausOnnistuu() {
        kassa.lataaRahaaKortille(kortti, 500);
        System.out.println(kortti.saldo());
        assertEquals(kassa.kassassaRahaa(), 100500);
        assertEquals(kortti.saldo(), 1500);
        
    }
    
    @Test
    public void negatiivinenLatausEiMuutaMitaan () {
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void liianVahanRahaaKortillaNiinEiLounasta() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 120);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void liianVahanKateistaNiinEiLounasta() {
        assertEquals(kassa.syoEdullisesti(230),230);
        assertEquals(kassa.syoMaukkaasti(230),230);
    }
    
    @Test
    public void lounasKateisellaOnnistuu(){
        kassa.syoEdullisesti(240);
        kassa.syoMaukkaasti(400);
        
        assertEquals(kassa.kassassaRahaa(), 100640);
        
    
    }
    
}
